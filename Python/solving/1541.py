# 1541 잃어버린 괄호
import sys
input = sys.stdin.readline


str = input().split('-')

sum = 0
for i in str[0].split('+'):
    sum+=int(i)
for i in str[1:]:
    for j in i.split('+'):
        sum -= int(j)

print(sum)
