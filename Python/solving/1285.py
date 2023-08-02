#1285 동전 뒤집기
import sys
input = sys.stdin.readline

n = int(input())

matrix = [list(input()) for _ in range(n)]
res = 1e21


for all in range(1<<n):
    tmp =[matrix[i][:] for i in range(n)]
    for i in range(n):
        if all & (1<<i): #해당 케이스 경우 검사
            for j in range(n):
                if tmp[i][j]=='H':
                    tmp[i][j]='T'
                else:
                    tmp[i][j] = 'H'

    total = 0
    for i in range(n):
        cnt = 0
        for j in range(n):
            if tmp[j][i] == 'T':
                cnt += 1
        total += min(cnt, n-cnt) #세로중 그냥 뒤집거나 안뒤집거나
    res = min(res, total)

print(res)