import random

n = 100
for _ in range(n):
    row = [str(random.randint(1, 1000)) for _ in range(n)]
    print(' '.join(row))
