import json
import requests
def api_commute(dialect):
    # dialect serialization
    dialect_dict = {"dialect":f"{dialect}"}
    request_dialect = json.dumps(dialect_dict)

    # standard transfer, jeju model api request
    response = requests.post("http://4.194.73.164:8010/jeju", data=request_dialect)
    print(response.json()["standard"])


if __name__=="__main__":
    api_commute("화났수꽈")