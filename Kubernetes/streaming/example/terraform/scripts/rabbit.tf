# RabbitMQ 환경 변수와 사용자 정보를 저장하는 Kubernetes Secret
resource "kubernetes_secret" "rabbitmq_secret" {
  metadata {
    name      = "rabbitmq-secret"
    namespace = "default"
  }

  data = {
    RABBITMQ_DEFAULT_USER = base64encode("admin")    # RabbitMQ 사용자
    RABBITMQ_DEFAULT_PASS = base64encode("password") # RabbitMQ 비밀번호
  }
}

# RabbitMQ 설정 파일을 저장하는 ConfigMap
resource "kubernetes_config_map" "rabbitmq_config" {
  metadata {
    name      = "rabbitmq-config"
    namespace = "default"
  }

  data = {
    "rabbitmq.conf" = <<EOT
log.console = true
listeners.tcp.default = 5672
management.listener.port = 15672
management.listener.ssl = false
EOT
  }
}

# RabbitMQ Deployment
resource "kubernetes_deployment" "rabbitmq" {
  metadata {
    name      = "rabbitmq"
    namespace = "default"
    labels = {
      app = "rabbitmq"
    }
  }

  spec {
    replicas = 1

    selector {
      match_labels = {
        app = "rabbitmq"
      }
    }

    template {
      metadata {
        labels = {
          app = "rabbitmq"
        }
      }

      spec {
        container {
          name  = "rabbitmq"
          image = "rabbitmq:3-management" # RabbitMQ with management plugin

          port {
            container_port = 5672 # AMQP Port
          }

          port {
            container_port = 15672 # Management UI Port
          }

          env {
            name  = "RABBITMQ_DEFAULT_USER"
            value_from {
              secret_key_ref {
                name = kubernetes_secret.rabbitmq_secret.metadata[0].name
                key  = "RABBITMQ_DEFAULT_USER"
              }
            }
          }

          env {
            name  = "RABBITMQ_DEFAULT_PASS"
            value_from {
              secret_key_ref {
                name = kubernetes_secret.rabbitmq_secret.metadata[0].name
                key  = "RABBITMQ_DEFAULT_PASS"
              }
            }
          }

          volume_mount {
            name       = "rabbitmq-config-volume"
            mount_path = "/etc/rabbitmq"
          }

          readiness_probe {
            http_get {
              path   = "/api/health/checks/alarms"
              port   = 15672
              scheme = "HTTP"
            }
            initial_delay_seconds = 5
            period_seconds        = 10
          }

          liveness_probe {
            http_get {
              path   = "/api/health/checks/alarms"
              port   = 15672
              scheme = "HTTP"
            }
            initial_delay_seconds = 15
            period_seconds        = 20
          }
        }

        volume {
          name = "rabbitmq-config-volume"

          config_map {
            name = kubernetes_config_map.rabbitmq_config.metadata[0].name
          }
        }
      }
    }
  }
}

# RabbitMQ ClusterIP Service
resource "kubernetes_service" "rabbitmq" {
  metadata {
    name      = "rabbitmq-service"
    namespace = "default"
  }

  spec {
    selector = {
      app = "rabbitmq"
    }

    port {
      port        = 5672
      target_port = 5672
    }

    port {
      port        = 15672
      target_port = 15672
    }

    type = "ClusterIP" # Internal service
  }
}