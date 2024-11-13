# ECR 리포지토리 생성
resource "aws_ecr_repository" "flixtube" {
  name                 = var.app_name
  image_scanning_configuration {
    scan_on_push = true
  }

  tags = {
    Environment = "Practice"
    Project     = "MyProject"
  }
}

# IAM 역할 생성
resource "aws_iam_role" "flixtube_role" {
  name = var.app_name
  assume_role_policy = jsonencode({
    Version = "2012-10-17",
    Statement = [
      {
        Action = "sts:AssumeRole",
        Effect = "Allow",
        Principal = {
          Service = "ecr.amazonaws.com"
        }
      }
    ]
  })
}

# ECR 리포지토리 정책 - 동적으로 IAM 역할 ARN을 참조
resource "aws_ecr_repository_policy" "flixtube_policy" {
  repository = aws_ecr_repository.flixtube.name

  policy = jsonencode({
    Version = "2012-10-17",
    Statement = [
      {
        Sid       = "AllowPushPull",
        Effect    = "Allow",
        Principal = {
          AWS = aws_iam_role.flixtube_role.arn  # 동적으로 참조
        },
        Action    = [
          "ecr:GetDownloadUrlForLayer",
          "ecr:BatchGetImage",
          "ecr:BatchCheckLayerAvailability",
          "ecr:PutImage"
        ]
      }
    ]
  })

  depends_on = [aws_iam_role.flixtube_role]  # 역할 생성 후 정책 적용
}

# IAM 역할에 ECR 전체 접근 정책 연결
resource "aws_iam_role_policy_attachment" "ecr_access" {
  role       = aws_iam_role.flixtube_role.name
  policy_arn = "arn:aws:iam::aws:policy/AmazonEC2ContainerRegistryFullAccess"
}