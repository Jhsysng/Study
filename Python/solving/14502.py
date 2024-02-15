# 14502 연구소
import sys
from collections import deque
import copy
input = sys.stdin.readline
dx = [0, 1, 0, -1]
dy = [1, 0, -1, 0]
n, m = map(int, input().split())

result = 0

matrix = [list(map(int, input().split())) for _ in range(n)]
def bfs():
    queue = deque()
    tmp = copy.deepcopy(matrix)
    for i in range(n):
        for k in range(m):
            if tmp[i][k] == 2:
                queue.append((i,k))
    while queue:
        x,y = queue.popleft()

        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]

            if 0 <= nx < n and 0 <= ny < m:
                if tmp[nx][ny] == 0:
                    tmp[nx][ny] = 2
                    queue.append((nx, ny))

    count = 0

    for i in range(n):
        for k in range(m):
            if tmp[i][k] == 0:
                count+=1
    global result
    result = max(result, count)

def wall(count):
    if count == 3:
        bfs()
        return
    for i in range(n):
        for k in range(m):
            if matrix[i][k] == 0:
                matrix[i][k] = 1
                wall(count+1)
                matrix[i][k] = 0

wall(0)

print(result)