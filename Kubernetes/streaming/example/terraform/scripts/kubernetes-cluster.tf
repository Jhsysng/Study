# 보안 그룹 설정

# EKS 클러스터용 보안 그룹
resource "aws_security_group" "eks_cluster_sg" {
  name        = "${var.app_name}-eks-cluster-sg"
  description = "Security group for EKS Cluster"
  vpc_id      = local.vpc_id

  # 인바운드 규칙은 별도의 aws_security_group_rule 리소스로 관리합니다.

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}

# EKS 작업자 노드용 보안 그룹
resource "aws_security_group" "eks_worker_sg" {
  name        = "${var.app_name}-eks-worker-sg"
  description = "Security group for EKS Worker Nodes"
  vpc_id      = local.vpc_id

  # 인바운드 규칙은 별도의 aws_security_group_rule 리소스로 관리합니다.

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}

# 클러스터 보안 그룹에서 작업자 노드 보안 그룹으로 트래픽 허용
resource "aws_security_group_rule" "eks_cluster_ingress" {
  type                     = "ingress"
  from_port                = 443
  to_port                  = 443
  protocol                 = "tcp"
  security_group_id        = aws_security_group.eks_cluster_sg.id
  source_security_group_id = aws_security_group.eks_worker_sg.id
}

# 작업자 노드 보안 그룹에서 클러스터 보안 그룹으로 트래픽 허용
resource "aws_security_group_rule" "eks_worker_ingress" {
  type                     = "ingress"
  from_port                = 443
  to_port                  = 443
  protocol                 = "tcp"
  security_group_id        = aws_security_group.eks_worker_sg.id
  source_security_group_id = aws_security_group.eks_cluster_sg.id
}

# EKS 클러스터용 IAM 역할
resource "aws_iam_role" "eks_cluster_role" {
  name = "${var.app_name}_eks_cluster_role"

  assume_role_policy = jsonencode({
    Version = "2012-10-17",
    Statement = [
      {
        Action    = "sts:AssumeRole",
        Effect    = "Allow",
        Principal = {
          Service = "eks.amazonaws.com"
        }
      }
    ]
  })
}

# EKS 클러스터 IAM 역할 정책 연결
resource "aws_iam_role_policy_attachment" "eks_cluster_policy" {
  role       = aws_iam_role.eks_cluster_role.name
  policy_arn = "arn:aws:iam::aws:policy/AmazonEKSClusterPolicy"
}

# EKS 작업자 노드용 IAM 역할
resource "aws_iam_role" "eks_worker_role" {
  name = "${var.app_name}_eks_worker_role"

  assume_role_policy = jsonencode({
    Version = "2012-10-17",
    Statement = [
      {
        Action    = "sts:AssumeRole",
        Effect    = "Allow",
        Principal = {
          Service = "ec2.amazonaws.com"
        }
      }
    ]
  })
}

# EKS 작업자 노드 IAM 역할 정책 연결

# AmazonEKSWorkerNodePolicy 연결
resource "aws_iam_role_policy_attachment" "eks_worker_node_policy" {
  role       = aws_iam_role.eks_worker_role.name
  policy_arn = "arn:aws:iam::aws:policy/AmazonEKSWorkerNodePolicy"
}

# AmazonEKS_CNI_Policy 연결
resource "aws_iam_role_policy_attachment" "eks_cni_policy_attachment" {
  role       = aws_iam_role.eks_worker_role.name
  policy_arn = "arn:aws:iam::aws:policy/AmazonEKS_CNI_Policy"
}

# AmazonEC2ContainerRegistryReadOnly 연결
resource "aws_iam_role_policy_attachment" "ec2_container_registry_read_only" {
  role       = aws_iam_role.eks_worker_role.name
  policy_arn = "arn:aws:iam::aws:policy/AmazonEC2ContainerRegistryReadOnly"
}

# EKS 클러스터 생성
resource "aws_eks_cluster" "flixtube_cluster" {
  name     = var.app_name
  role_arn = aws_iam_role.eks_cluster_role.arn

  vpc_config {
    subnet_ids         = local.subnet_ids
    security_group_ids = [aws_security_group.eks_cluster_sg.id]
  }

  version = "1.25"

  tags = {
    Environment = "Practice"
    Project     = "MyProject"
  }
}

# EKS 작업자 노드 그룹 생성
resource "aws_eks_node_group" "flixtube_node_group" {
  cluster_name    = aws_eks_cluster.flixtube_cluster.name
  node_group_name = "${var.app_name}-node-group"
  node_role_arn   = aws_iam_role.eks_worker_role.arn
  subnet_ids      = local.subnet_ids

  scaling_config {
    desired_size = 2
    max_size     = 3
    min_size     = 1
  }

  instance_types = ["t3.micro"]
  ami_type       = "AL2_x86_64"  # Amazon Linux 2 EKS 최적화 AMI

  tags = {
    Name        = "flixtube-node-group"
    Environment = "Practice"
  }
}