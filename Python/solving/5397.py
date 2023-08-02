#5397키로거

import sys

n = int(sys.stdin.readline())

for _ in range(n):

    left = []
    right = []

    pwd = list(sys.stdin.readline().rstrip())

    for char in pwd:

        if char == ">":
            if right:
                left.append(right.pop())

        elif char=="<":
            if left:
                right.append(left.pop())

        elif char=="-":
            if left:
                left.pop()

        else:
            left.append(char)

    print("".join(left) + "".join(reversed(right)))