# 2503 숫자야구
import sys
input = sys.stdin.readline
from itertools import permutations

case = int(input())
num = ['1', '2', '3', '4', '5', '6', '7', '8', '9']
permutate = list(permutations(num, 3))

for _ in range(case):
    test, s, b = map(int, input().split())
    test = list(str(test))
    rmv_cnt = 0
    for i in range(len(num)):
        strike = ball = 0
        i -= rmv_cnt

        for j in range(3):
            if permutate[i][j] == test[j]:
                strike +=1
            elif test[j] in permutate[i]:
                ball +=1
        if strike != s or ball != b:
            num.remove(num[i])
            rmv_cnt +=1
print(len(num))
