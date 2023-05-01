import requests
from bs4 import BeautifulSoup

url = ""
#
r = requests.get(url)
soup = BeautifulSoup(r.text, "html.parser")
choose = soup.select_one("#content-text")
print(choose)
chooses = soup.select("#sub > div.container > div:nth-child(2) > div.col-lg-9.col-md-9.col-sm-12.col-xs-12.sub-newcontents > div.iframe-responsive > div > div.table-responsive > table > tbody > tr > td")

for c in chooses:
    print(c)