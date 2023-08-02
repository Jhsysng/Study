#17836 공주님을 구해라

import sys
from collections import deque


input = sys.stdin.readline
n, m, t = map(int, input().split())
dx = [-1, 1 , 0, 0]
dy = [0, 0, -1, 1]

matrix = [list(map(int,input().split())) for _ in range(n)]

q = deque()
visited = [[0]*m for _ in range(n)]

def gram_bfs():
    time = 1e9
    q.append((0,0))
    visited[0][0] = 1
    while q:
        x, y = q.popleft()
        if (x, y) == (n-1, m-1):
            return min(visited[x][y]-1, time)
        if matrix[x][y]==2:
            time = visited[x][y]-1 + n-1-x + m-1-y

        for i in range(4):
            nx, ny = x+dx[i], y+dy[i]
            if 0<= nx< n and 0 <= ny < m and not visited[nx][ny]:
                if matrix[nx][ny] == 0 or matrix[nx][ny] == 2:
                    visited[nx][ny] = visited[x][y] + 1
                    q.append((nx,ny))

    return time

result = gram_bfs()

if result > t:
    print("Fail")
else:
    print(result)