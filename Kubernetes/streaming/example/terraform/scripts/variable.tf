variable app_name {
    description = "value of the application name"
    default = "flixtube"
}

variable location {
    description = "value of the region"
    default = "us-west-2"
}


# 기본 VPC 가져오기
data "aws_vpc" "default" {
  default = true
}

# 기본 VPC의 서브넷 가져오기
data "aws_subnets" "default" {
  filter {
    name   = "vpc-id"
    values = [data.aws_vpc.default.id]
  }
}

# 로컬 변수로 기본 VPC 및 서브넷 ID 설정
locals {
  vpc_id     = data.aws_vpc.default.id
  subnet_ids = data.aws_subnets.default.ids
}