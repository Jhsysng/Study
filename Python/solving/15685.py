# 15685 드래곤 커브

import sys
input = sys.stdin.readline

dx = [1, 0, -1, 0]
dy = [0, -1, 0, 1]
n = int(input())
# x, y, d, g
curve = [list(map(int, input().split())) for _ in range(n)]
final = []
def rotate_paste(tmp):
    new = []
    px, py = tmp[-1]
    for i in range(len(tmp) - 2, -1, -1):
        x, y = tmp[i]
        translated_x = x - px
        translated_y = y - py

        rotated_x = -translated_y
        rotated_y = translated_x

        final_x = rotated_x + px
        final_y = rotated_y + py

        tmp.append((final_x, final_y))

    tmp.extend(new)
    return tmp




def count_squares(coords):
    count = 0
    coords = set(coords)
    for coord in coords:
        x, y = coord
        if (x + 1, y) in coords and (x, y + 1) in coords and (x + 1, y + 1) in coords:
            count += 1
    return count


for i in range(len(curve)):
    tmp = []
    x, y, d, g = curve[i]
    tmp.append((x, y))
    nx = x + dx[d]
    ny = y + dy[d]
    tmp.append((nx, ny))
    for _ in range(g):
        tmp = rotate_paste(tmp)

    final.extend(tmp)

ans = count_squares(final)
print(ans)



