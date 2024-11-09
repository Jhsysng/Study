variable app_name {
    description = "value of the application name"
    default = "flixtube"
}

variable location {
    description = "value of the region"
    default = "us-west-2"
}


variable "vpc_id" {
  description = "EKS 클러스터가 배포될 VPC ID"
  type        = string
}

variable "subnet_ids" {
  description = "EKS 클러스터의 서브넷 ID 목록"
  type        = list(string)
}

variable "ec2_key_pair_name" {
  description = "EC2 인스턴스 접근을 위한 키 페어 이름"
  type        = string
}