#경비원

import sys
input = sys.stdin.readline

width, height = map(int, sys.stdin.readline().split())

n = int(input().strip())

store_list = []

def calc_dist(idx, p):
    if idx == 1:
        return p
    elif idx == 2:
        return width*2 + height - p
    elif idx == 3:
        return width*2 + height*2 - p
    elif idx == 4:
        return width + p

cricular = (width+height)*2

dist = []

for i in range(n+1):
    idx, p = map(int, input().split())
    dist.append(calc_dist(idx, p))

dist_ = dist[-1]

ans = 0

for i in range(n):
    len = abs(dist_ - dist[i])
    ans += min(len, cricular-len)

print(ans)