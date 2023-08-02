# 2108 통계학

import sys

input = sys.stdin.readline

n = int(input())
arr = []

for i in range(n):
    arr.append(int(input()))

arr.sort()

print(round(sum(arr) / len(arr)))
print(arr[len(arr) // 2])  # 중앙값

dic = dict()
for i in arr:  # 빈
    if i in dic:
        dic[i] += 1
    else:
        dic[i] = 1

mx = max(dic.values())
mx_dic = []

for i in dic:
    if mx == dic[i]:
        mx_dic.append(i)

if len(mx_dic) > 1:
    print(mx_dic[1])
    print(mx_dic[0])

print(max(arr) - min(arr))