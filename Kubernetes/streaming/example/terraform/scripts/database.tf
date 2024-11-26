# MongoDB 초기 계정 정보를 저장하는 Kubernetes Secret
resource "kubernetes_secret" "mongodb_secret" {
  metadata {
    name      = "mongodb-secret"
    namespace = "default"
  }

  data = {
    MONGO_INITDB_ROOT_USERNAME = base64encode("admin")    # 관리자 계정
    MONGO_INITDB_ROOT_PASSWORD = base64encode("password") # 관리자 비밀번호
  }
}

# MongoDB 접근을 위한 Headless Service
resource "kubernetes_service" "mongodb_service" {
  metadata {
    name      = "mongodb-service"
    namespace = "default"
  }

  spec {
    selector = {
      app = "mongodb"
    }

    port {
      port        = 27017       # 외부 포트
      target_port = 27017       # 컨테이너 내부 포트
    }

    cluster_ip = "None" # StatefulSet과 연결
  }
}

# MongoDB 데이터 저장을 위한 Persistent Volume Claim
resource "kubernetes_persistent_volume_claim" "mongodb_pvc" {
  metadata {
    name      = "mongodb-pvc"
    namespace = "default"
  }

  spec {
    access_modes = ["ReadWriteOnce"]

    resources {
      requests = {
        storage = "5Gi" # 요청된 스토리지 크기
      }
    }
  }
}

# MongoDB StatefulSet으로 Pod 관리
resource "kubernetes_stateful_set" "mongodb" {
  metadata {
    name      = "mongodb"
    namespace = "default"
  }

  spec {
    replicas             = 1                      # 복제본 개수
    service_name         = kubernetes_service.mongodb_service.metadata[0].name
    pod_management_policy = "Parallel"            # 병렬로 Pod 관리

    selector {
      match_labels = {
        app = "mongodb"
      }
    }

    template {
      metadata {
        labels = {
          app = "mongodb" # Pod 라벨
        }
      }

      spec {
        termination_grace_period_seconds = 10

        container {
          name  = "mongodb"
          image = "mongo:5.0"   # MongoDB 이미지 버전

          port {
            container_port = 27017
          }

          volume_mount {
            name       = "mongodb-storage"
            mount_path = "/data/db" # 데이터 저장 경로
          }

          # 환경 변수로 관리자 계정 정보 전달
          env {
            name  = "MONGO_INITDB_ROOT_USERNAME"
            value_from {
              secret_key_ref {
                name = kubernetes_secret.mongodb_secret.metadata[0].name
                key  = "MONGO_INITDB_ROOT_USERNAME"
              }
            }
          }

          env {
            name  = "MONGO_INITDB_ROOT_PASSWORD"
            value_from {
              secret_key_ref {
                name = kubernetes_secret.mongodb_secret.metadata[0].name
                key  = "MONGO_INITDB_ROOT_PASSWORD"
              }
            }
          }
        }

        # 데이터 저장을 위한 Persistent Volume 연결
        volume {
          name = "mongodb-storage"

          persistent_volume_claim {
            claim_name = kubernetes_persistent_volume_claim.mongodb_pvc.metadata[0].name
          }
        }
      }
    }
  }
}