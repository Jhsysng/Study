# 최단경로 1753
# 다익스트라
import sys
import heapq

input = sys.stdin.readline

V, e = map(int, input().split())
start = int(input())

INF = 10000000000
distance = [INF]*(V+1)
graph = [[] for _ in range(V+1)]
heap = []

for _ in range(e):
    u, v, w= map(int, input().split())
    graph[u].append((w, v))


def dijkstra(start):
    distance[start]=0
    heapq.heappush(heap, (0, start))

    while heap:
        cur_w, cur = heapq.heappop(heap)
        if distance[cur] < cur_w:
            continue
        for w, target_v in graph[cur]:
            next_w = w + cur_w
            if next_w < distance[target_v]:
                distance[target_v] = next_w
                heapq.heappush(heap,(next_w, target_v))

dijkstra(start)
for i in range(1,V+1):
    if distance[i] == 10000000000:
        print("INF")
    else:
        print(distance[i])