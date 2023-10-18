# 1987 알파벳
import sys
input = sys.stdin.readline

a, b = map(int,input().split())

matrix = map(int, input().split())
ans = 0
alphabet = set()
for _ in range(a):
    matrix.append(list(input()))

dx = [-1, 1, 0 , 0]
dy = [0, 0, -1, 1]
def dfs(x, y, count):
    global ans
    ans = max(ans, count)
    for i in range(4):
        nx = x + dx[i]
        ny = y + dy[i]
        if 0 <= nx < a and 0<=ny < b and not matrix[nx][ny] in alphabet:
            alphabet.add(matrix[nx][ny])
            dfs(nx, ny, count+1)
            alphabet.remove(matrix[nx][ny])

alphabet.add(matrix[0][0])

dfs(0, 0, 1)
print(ans)
