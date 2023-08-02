import math
n = int(input())
if n == 3:
    print("0")
else:
    print(int(math.factorial(n)/(24*math.factorial(n-4))))