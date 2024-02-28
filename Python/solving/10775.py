# 10775 공항
import sys
input = sys.stdin.readline

g = int(input())
p = int(input())

gate = [0]*g
ans = 0
gl = len(gate)
gate_origin = [i for i in range(g+1)]

def find(plane):
    if gate_origin[plane] == plane:
        return plane
    gate_origin[plane] = find(gate_origin[plane])
    return gate_origin[plane]

for i in range(p):
    tmp = int(input())
    tmp = find(tmp)
    if tmp == 0:
        break
    gate_origin[tmp] = gate_origin[tmp-1]
    ans+=1


print(ans)
