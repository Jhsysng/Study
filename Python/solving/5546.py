# 55467 파스타

import sys
input = sys.stdin.readline
n, k = map(int,input().split())

dp = [[[0 for _ in range(2)] for _ in range(4)] for _ in range(n + 1)]
e = [0]*(n+1)
for _ in range(k):
    a, b = map(int, input().split())
    e[a] = b

def cal(a, b, c):
    if e[a] and e[a] ^ b:
        return 0
    elif a==n:
        return 1
    elif dp[a][b][c]:
        return dp[a][b][c]
    ans = 0 if c else cal(a + 1, b, 1)

    for i in range(1,4):
        if b ^ i:
            ans = (ans+cal(a+1, i, 0))%10000
    dp[a][b][c] = ans
    return ans

print(cal(0,0,1))