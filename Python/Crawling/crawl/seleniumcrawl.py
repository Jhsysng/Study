from selenium import webdriver
from PIL import Image

url = "https://shop.hakhub.net/product/flying-ninja/"

options = webdriver.ChromeOptions()
options.add_argument("window-size=1920,1080")
options.add_argument("lang-ko_KR")

driver = webdriver.Chrome("~/Downloads/chromedriver_mac_arm64/chromedriver", options=options)
driver.get(url)
driver.implicitly_wait(3)
driver.get_screenshot_as_file("web.png")

Image.open("web.png").convert("RGB").save("web.jpg", quality=100)
im = Image.open("web.png")
cropped_image = im.crop((280, 300, 1100, 780))
cropped_image.save("web_cropped.png", quality=100)

driver.close()