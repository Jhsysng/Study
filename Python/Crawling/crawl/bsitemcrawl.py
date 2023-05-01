import requests
from bs4 import BeautifulSoup

url = "http://www.jeju.go.kr/culture/dialect/dictionary.htm"

r = requests.get(url)
soup = BeautifulSoup(r.text, "html.parser")
choose = soup.select_one("#sub > div.container > div:nth-child(2) > div.col-lg-9.col-md-9.col-sm-12.col-xs-12.sub-newcontents > div.iframe-responsive > div > div.table-responsive > table > tbody > tr")
print(choose)
chooses = soup.select("#sub > div.container > div:nth-child(2) > div.col-lg-9.col-md-9.col-sm-12.col-xs-12.sub-newcontents > div.iframe-responsive > div > div.table-responsive > table > tbody > tr > a")
for c in chooses:
    print(c)