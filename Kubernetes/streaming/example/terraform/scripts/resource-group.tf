# resource_group.tf

provider "aws" {
  region = "us-west-2"
}

# 리소스 그룹 생성
resource "aws_resourcegroups_group" "terraform-example" {
  name        = "terraform-ex"   # 리소스 그룹 이름
  description = "This is an example resource group created with Terraform."

  resource_query {
    query = jsonencode({
      ResourceTypeFilters = ["AWS::AllSupported"]
      TagFilters = [
        {
          Key    = "Environment"
          Values = ["Development"]
        }
      ]
    })
  }
}