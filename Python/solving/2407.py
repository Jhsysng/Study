#2407 조합
from math import comb;print(comb(*map(int,input().split())))

import sys
from math import comb
input = sys.stdin.readline
n , m = map(int, input().split())
print(comb(n, m))
