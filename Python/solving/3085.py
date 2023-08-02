import sys

input = sys.stdin.readline

n = int(input())
matrix = list(list(map(int, input().split())) for _ in range(n))

cnt = 0


def width():
    global maxCount

    for k in range(n):
        countRow = 1  # 초기 개수를 1로 초기화
        for l in range(n - 1):
            if matrix[k][l] == matrix[k][l + 1]:  # 만약 같은 열의 사탕의 색이 같다면
                countRow += 1  # 사탕 개수 1 증가
                maxCount = max(maxCount, countRow)  # 증가시킨 값과 최대 사탕개수를 비교하여 큰값을 대입
            else:  # 만약 같은 열의 사탕 개수가 다르다면
                countRow = 1

def height():
    for k in range(n):
        global maxCount

        countColumn = 1  # 초기 개수를 1로 초기화
        for l in range(n - 1):
            if matrix[l][k] == matrix[l + 1][k]:  # 만약 같은 행의 사탕의 색이 같다면
                countColumn += 1  # 사탕 개수를 1개씩 증가시켜주고
                maxCount = max(maxCount, countColumn)  # 증가시킨 값과 최대 사탕개수를 비교하여 큰값을 대입
            else:  # 만약 같은 행의 색이 다르다면
                countColumn = 1  # 개수를 1로 초기화


for i in range(n):
    for j in range(n - 1):
        if matrix[i][j] != matrix[i][j + 1]:
         matrix[i][j], matrix[i][j + 1] = matrix[i][j + 1], matrix[i][j]
            width()
            height()
         matrix[i][j + 1], matrix[i][j] = matrix[i][j], matrix[i][j + 1]
        if matrix[j][i] != matrix[j + 1][i]:
         matrix[j][i], matrix[j + 1][i] = matrix[j + 1][i], matrix[j][i]
            width()
            height()
         matrix[j + 1][i], matrix[j][i] = matrix[j][i], matrix[j + 1][i]

print(maxCount)