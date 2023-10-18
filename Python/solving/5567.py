# 5567 결혼식

from sys import stdin

n = int(stdin.readline().strip())
graph = [[] for _ in range(n + 1)]
visited = [0] * (n + 1)
for _ in range(int(stdin.readline().strip())):
	x, y = map(int, stdin.readline().split())
	graph[y].append(x)
	graph[x].append(y)

cnt = 0
visited[1] = 1
for i in graph[1]:
    if not visited[i]:
        visited[i] = 1
        cnt += 1
    for j in graph[i]:
        if not visited[j]:
            visited[j] = 1
            cnt += 1

print(cnt)