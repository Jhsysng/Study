#2468 안전영역

import sys
import copy
sys.setrecursionlimit(100000)
input = sys.stdin.readline

n = int(input())

dx = [1, -1, 0, 0]
dy = [0, 0, -1, 1]

matrix = [list(map(int, input().split()))for _ in range(n)]

def dfs(d, x,y):
    visit[x][y] = True
    for i in range(4):
        nx = x + dx[i]
        ny = y + dy[i]

        if 0<= nx< n and 0 <= ny < n and matrix[nx][ny]>d and not visit[nx][ny]:
            dfs(d, nx,ny)
ans = 0
for i in range(max(map(max, matrix))):
    visit = [[False]*n for _ in range(n)]
    count = 0
    for j in range(n):
        for k in range(n):
            if matrix[j][k] > i and not visit[j][k]:
                count += 1
                visit[j][k] = True
                dfs(i, j, k)
    ans = max(ans, count)

print(ans)