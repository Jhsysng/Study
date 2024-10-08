#17845 수강과목
import sys
input = sys.stdin.readline

n, k = map(int, input().split())
dp = [[0]*(n+1) for _ in range(k+1)]
time = []
ipt = []
for _ in range(k):
    i, t = map(int, input().split())
    time.append(t)
    ipt.append(i)

for i in range(1, k+1):
    for j in range(1,n+1):
        if time[i-1] > j:
            dp[i][j] = dp[i-1][j]
        else:
            dp[i][j] = max(ipt[i - 1] + dp[i - 1][j - time[i - 1]], dp[i - 1][j])
print(dp[k][n])
