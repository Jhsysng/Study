#18111 마인크래프트

import sys
input = sys.stdin.readline

n, m, b = map(int, input().split())

matrix = [list(map(int,input().split())) for _ in range(n)]

time = 100000
height = 0
for h in range(257):
        up = 0
        down = 0
        for i in range(n):
                for j in range(m):
                        if matrix[i][j] > h:
                                down += matrix[i][j] - h
                        else:
                                up -= h - matrix[i][j]

        if down + b >= up:
                tmp = down*2 + up
                if time >= tmp:
                        time = tmp
                        height = h
print(time, height)