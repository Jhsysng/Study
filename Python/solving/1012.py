import sys
sys.setrecursionlimit(10000)

input = sys.stdin.readline

dx = [1, -1, 0, 0]
dy = [0, 0, -1, 1]
test = int(input())
def dfs(x, y):
    if x<=-1 or x>=m or y<=-1 or y>=n:
        return False
    if graph[x][y]==1:
        graph[x][y] = 0 # 다녀간곳
        for i in range(4):
            dfs(x+dx[i], y+dy[i])
        return True
    return False

for _ in range(test):
    n, m, c = list(map(int, input().split()))
    graph = [[0]*n for _ in range(m)]
    for i in range(c):
        x, y = map(int, input().split())
        graph[y][x] = 1
    count = 0
    for i in range(m):
        for j in range(n):
            if dfs(i, j):
                count += 1

    print(count)