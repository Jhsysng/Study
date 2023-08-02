import sys

input = sys.stdin.readline

n = int(input())

arr = [0]*10000

for i in range(n):
    arr[i] = int(input())

sum=[0]*10000
sum[0]=arr[0]
sum[1]=arr[0]+arr[1]
sum[2]=max(arr[2]+arr[0], arr[2]+arr[1], sum[1])
for i in range(3,n):
  sum[i] = max(arr[i]+sum[i-2], arr[i]+arr[i-1]+sum[i-3], sum[i-1])

print(max(sum))
