import sys
from itertools import combinations

input = sys.stdin.readline

n, m = map(int, input().split())
city = list(list(map(int, input().split())) for _ in range(n))
result = 2**14
home = []
chicken = []

for i in range(n):
    for j in range(n):
        if city[i][j] is 1:
            home.append([i,j])
        elif city[i][j] is 2:
            chicken.append([i, j])

for chi in combinations(chicken, m):
    distance = 0
    for h in home:
        chiken_d = n + 1
        for j in range(m):
            chiken_d = min(chiken_d, abs(h[0]-chi[j][0]) + abs(h[1] - chi[j][1]))
        distance += chiken_d
    result = min(result, distance)

print(result)