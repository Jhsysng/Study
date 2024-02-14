# 1074 z

import sys
input = sys.stdin.readline

n, r, c = map(int, input().split())

arr = []

a = 2 ** n
x, y = r, c

while a >= 2:
    a //= 2
    if x < a and y < a:
        arr.append(0)
    elif x < a and y >= a:
        arr.append(1)
        y -= a
    elif x >= a and y < a:
        arr.append(2)
        x -= a
    else:
        arr.append(3)
        x -= a
        y -= a



arr.reverse()
i = 0
ans = 0
while i < len(arr):
    ans += arr[i]* (4**i)
    i+=1

print(ans)