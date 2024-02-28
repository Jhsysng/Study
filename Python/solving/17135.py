# 17135 캐슬 디펜스

import sys
import copy
import itertools
from collections import deque

input = sys.stdin.readline
n, m, d = map(int, input().split())
dx = [0, -1, 0]
dy = [-1, 0, 1]
candidate = [i for i in range(m)]
archer = list(itertools.combinations(candidate, 3))
matrix = []
for _ in range(n):
    matrix.append(list(map(int,input().split())))

matrix_case = []
archer_len = len(archer)
for _ in range(archer_len):
    matrix_case.append(copy.deepcopy(matrix))




def next_turn(matrix):
    for i in range(n-1 , 0, -1):
        matrix[i]=matrix[i-1]
    matrix[0] = [0 for _ in range(m)]
    return matrix

simul = 0

def get_distance(ax, ay, bx, by):
    return abs(ax - bx) + abs(ay - by)

def find_targets(matrix, archer_position):
    targets = []
    for ax, ay in archer_position:
        closest_target = None
        min_distance = d + 1
        for x in range(n):
            for y in range(m):
                if matrix[x][y] == 1:
                    distance = get_distance(ax, ay, x, y)
                    if distance <= d:
                        if closest_target is None or distance < min_distance or (distance == min_distance and y < closest_target[1]):
                            closest_target = (x, y)
                            min_distance = distance
        if closest_target:
            targets.append(closest_target)
    return targets

def kill(matrix, archer_list):
    kill_sum = 0
    targets = find_targets(matrix, [(n, a) for a in archer_list])
    for target in set(targets):
        x, y = target
        if matrix[x][y] == 1:
            matrix[x][y] = 0
            kill_sum += 1
    return kill_sum, matrix

ans_list = [0]*archer_len

while simul < n:
    simul += 1
    for i in range(archer_len):
        tmp, matrix_case[i] = kill(matrix_case[i], archer[i])
        matrix_case[i] = next_turn(matrix_case[i])
        ans_list[i] += tmp

print(max(ans_list))

