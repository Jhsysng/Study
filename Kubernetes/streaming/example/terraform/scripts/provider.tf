# AWS 프로바이더 기본 설정
provider "aws" {
  region  = var.location
  profile = "screenshot161-cli"
}

# EKS 클러스터 정보 가져오기
data "aws_eks_cluster" "flixtube" {
  name = aws_eks_cluster.flixtube_cluster.name
}

data "aws_eks_cluster_auth" "flixtube" {
  name = aws_eks_cluster.flixtube_cluster.name
}

# Kubernetes 프로바이더 설정
provider "kubernetes" {
  host                   = data.aws_eks_cluster.flixtube.endpoint
  cluster_ca_certificate = base64decode(data.aws_eks_cluster.flixtube.certificate_authority.0.data)
  token                  = data.aws_eks_cluster_auth.flixtube.token
}

# Kubernetes 대시보드와 같은 별도의 컨텍스트를 위한 프로바이더 설정 (Optional)
provider "kubernetes" {
  alias                  = "dashboard"
  host                   = data.aws_eks_cluster.flixtube.endpoint
  cluster_ca_certificate = base64decode(data.aws_eks_cluster.flixtube.certificate_authority.0.data)
  token                  = data.aws_eks_cluster_auth.flixtube.token
}

# 기본 AWS VPC 정보 가져오기
data "aws_vpc" "default" {
  default = true
}

# VPC와 연결된 서브넷 정보 가져오기
data "aws_subnets" "default" {
  filter {
    name   = "vpc-id"
    values = [data.aws_vpc.default.id]
  }
}

# 로컬 변수 설정
locals {
  vpc_id     = data.aws_vpc.default.id
  subnet_ids = data.aws_subnets.default.ids
}