# resource_group.tf

# 리소스 그룹 생성
resource "aws_resourcegroups_group" "terraform-example" {
  name        = var.app_name
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