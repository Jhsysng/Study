import sys
input = sys.stdin.readline
list = input()

list = list.replace("XXXX", "AAAA")
list = list.replace("XX", "BB")

if 'X' in list:
    print(-1)
else:
    print(list)