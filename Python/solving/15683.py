import sys
import copy

input = sys.stdin.readline

n, m = map(int,input().split())

pos = []
room = []
cctv = [[[0],[1],[2],[3]],
        [[0,2],[1,3]],
        [[0,1],[1,2],[2,3],[0,3]],
        [[0,1,2],[0,1,3],[1,2,3],[0,2,3]],
        [[0,1,2,3]],]

# 90도 돌리기
dx = [-1, 0, 1, 0]
dy = [0, 1, 0, -1]

for i in range(n):
    p = list(map(int, input().split()))
    room.append(p)
    for j in range(m):
        if p[j] in [1, 2, 3, 4, 5]:
            pos.append([p[j],i,j]) # cctv위치와 cctv종류를 저장


def fill(temp, dir, x, y):
    for i in dir:
        nx, ny = x, y
        while True:
            nx += dx[i] #nx ny는 room의 현재 위치
            ny += dy[i]
            if nx < 0 or nx>=n or ny<0 or ny>=m or temp[nx][ny] == 6:
                break # 채우기 종료
            elif temp[nx][ny] == 0:
                temp[nx][ny] = '#' # fill


def dfs(dep, room):
    global min_value
    case = copy.deepcopy(room) # case 종류
    if dep == len(pos):
        count = 0
        for i in range(n):
            count += room[i].count(0)
        min_value = min(min_value, count)
        cctv_n, x, y = pos[dep]
        for i in cctv[cctv_n-1]: # 해당 cctv 돌려보기
            fill(case,i, x, y)
            dfs(dep+1, case)
            case = copy.deepcopy(room)


min_value = int(1e9)
dfs(0, room)
print(min_value)

