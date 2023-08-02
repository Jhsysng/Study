import sys

input = sys.stdin.readline

n = input()
res = [0] * 10
for i in range(len(n)-1):
    num = int(n[i])
    if num == 6 or num == 9:
        if res[6] <= res[9]:
            res[6] += 1
        else:
            res[9] += 1
    else:
        res[num] += 1
print(max(res))