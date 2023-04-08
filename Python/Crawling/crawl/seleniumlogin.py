from selenium import webdriver

url = "https://shop.hakhub.net/wp-login.php"

user_login = "customer01"
user_pass = "customer01!!"


def load_driver():
    options = webdriver.ChromeOptions()
    options.add_argument("window-size=1920,1080")
    options.add_argument("lang=ko_KR")
    return webdriver.Chrome("~/Downloads/chromedriver_mac_arm64/chromedriver", options=options)


def try_login():
    driver.get(url)
    driver.implicity_wait(3)

    for id in user_login:
        driver.find_element_by_name("log").send_keys(id)
    for pw in user_pass:
        driver.find_element_by_name("pwd").send_keys(pw)

    driver.find_element_by_name("wp_submit").click()


if __name__=="__main__":
    driver = load_driver()
    try_login()