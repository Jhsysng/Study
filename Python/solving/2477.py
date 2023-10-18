# 외밭
import sys
input = sys.stdin.readline

count = int(input())

len = []
dir = []

for i in range(6):
    d, l = map(int, input().split())
    dir.append(d)
    len.append(l)
s
big_len = []
small_len = []

for i in range(1,5):
    if dir.count(i)==1:
        big_len.append(len[dir.index(i)])
        small = dir.index(i)+3
        if small >=6:
            small -= 6
        small_len.append(len[small])

big = big_len[0]*big_len[1]
small = small_len[0]*small_len[1]

print(count*(big-small))
