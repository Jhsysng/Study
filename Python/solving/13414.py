#13414

import sys
input = sys.stdin.readline

k, l = map(int, input().split())
dict = {}
for i in range(l):
  dict[input().strip()] = i

sorted_dict = sorted(dict.items(), key=lambda x: x[1])

for i in range(k):
  if i < len(sorted_dict):
    print(sorted_dict[i][0])
  else:
    break