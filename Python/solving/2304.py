# 2304 창고 다각형

n = int(input())

store = [0]*1001
for _ in range(n):
    l, h = map(int,input().split())
    store[l]=h

max_idx=store.index(max(store))

max_h = 0
for i in range(max_idx):
    max_h = max(max_h, store[i])
    store[i] = max_h

max_h = 0
for i in range(1000, max_idx, -1):
    max_h = max(max_h, store[i])
    store[i] = max_h

print(sum(store))

