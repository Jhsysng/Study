import requests

login_url="https://shop.hakhub.net/wp-login.php"
item_url="https://shop.hakhub.net/wp-comments-post.php"
account_form_data = {"log": "customer01", "pwd": "customer01!!"}

with requests.Session() as s:
    r = s.post(login_url, account_form_data)
    comment_form_data={
        "rating": 5,
        "comment": "댓글 작성 테스트",
        "comment_post_ID": "70",
        "comment_parent": 0,

    }

    r = s.post(item_url, cookies=s.cookies, data=comment_form_data)
    if r.status_code==200:
        print("OK")
    elif r.status_code==403:
        print("403 Forbidden")
    elif r.status_code==409:
        print("409 Conflict")
    elif r.status_code==429:
        print("429 TMR")
    else:
        print(f"ERROR CODE {r.status_code}")
