# 1485 정사각형

import sys

input = sys.stdin.readline

case = int(input())
tmp = 0

def length(p1,p2):
    return abs((p2[0]-p1[0])**2+abs(p2[1]-p1[1])**2)

for _ in range(case):
    square = []
    for i in range(4):
        x, y = map(int,input().split())
        square.append([x,y])
    square = sorted(square)

    for nsqr in square:
        if nsqr == [0,0]:
            tmp+=1

    if tmp == 4:
        print(0)
        tmp =0
    elif length(square[0], square[1]) == length(square[0], square[2]) == length(square[1], square[3]) and length(square[1], square[2]) == length(square[0], square[3]):
        print(1)
    else:
        print(0)