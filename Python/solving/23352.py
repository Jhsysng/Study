# 23352 방탈출
import sys
from collections import deque
input = sys.stdin.readline

n, m = map(int, input().split())

matrix = [list(map(int, input().split())) for _ in range(n)]

dx = [0, 1, 0, -1]
dy = [1, 0, -1, 0]

ans = []
def bfs(cx, cy):
    x, y = cx, cy
    tmp = [[0]*m for _ in range(n)]
    q = deque()
    q.append((x, y, 0))
    visited = [[False]*m for _ in range(n)]
    while q:
        x, y, cnt = q.popleft()
        flag = False
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            if 0 <= nx < n and 0 <= ny < m and matrix[nx][ny]!=0 and not visited[nx][ny]:
                visited[nx][ny] = True
                flag = True
                q.append((ny,nx, cnt+1))
        if not flag:
            ans.append((cnt, matrix[cx][cy] + matrix[x][y]))

for i in range(n):
    for j in range(m):
        if matrix[i][j] != 0:
            bfs(i, j)

ans.sort(key = lambda x:(x[0], x[1]), reverse = True)

print(ans[0][1])





