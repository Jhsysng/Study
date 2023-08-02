#10189 차이를 최대로

import sys
from itertools import permutations
input = sys.stdin.readline

n = int(input())
input_list = list(map(int, input().split()))
per = permutations(input_list)
ans = 0

for i in per:
    s = 0
    for j in range(len(i) - 1):
        s += abs(i[j] - i[j + 1])
    if s > ans:
        ans = s

print(ans)