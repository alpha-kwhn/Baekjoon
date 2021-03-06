# 블럭의 대칭과 회전 경우까지 고려해야하므로 시간복잡도가 높을 수 밖에없음
# pypy3로 돌려야 해결가능

import sys
input = sys.stdin.readline

row, column = map(int, input().split())
game = [[0]*column]*row

for i in range(row):
    tmp = list(map(int, input().split()))
    game[i] = tmp

blocks = [[(0, 0), (0, 1), (0, 2), (0, 3)], [(0, 0), (1, 0), (2, 0), (3, 0)], [(0, 0), (0, 1), (1, 0), (1, 1)],
          [(0, 0), (1, 0), (2, 0), (2, 1)], [(0, 0), (0, 1), (0, 2), (1, 0)], [(0, 0), (0, 1), (1, 1), (2, 1)],
          [(0, 2), (1, 0), (1, 1), (1, 2)], [(0, 0), (1, 0), (1, 1), (2, 1)], [(0, 1), (0, 2), (1, 0), (1, 1)],
          [(0, 0), (0, 1), (0, 2), (1, 1)], [(0, 1), (1, 0), (1, 1), (2, 1)], [(0, 1), (1, 0), (1, 1), (1, 2)],
          [(0, 0), (1, 0), (1, 1), (2, 0)], [(0, 1), (1, 1), (1, 0), (2, 0)], [(0, 0), (0, 1), (1, 1), (1, 2)],
          [(0, 1), (1, 1), (2, 1), (2, 0)], [(0, 0), (0, 1), (0, 2), (1, 2)], [(0, 0), (0, 1), (1, 0), (2, 0)],
          [(0, 0), (1, 0), (1, 1), (1, 2)]]

result = []
for i in range(row):
    for j in range(column):
        for p in blocks:
            x1 = i + p[0][0]
            y1 = j + p[0][1]
            x2 = i + p[1][0]
            y2 = j + p[1][1]
            x3 = i + p[2][0]
            y3 = j + p[2][1]
            x4 = i + p[3][0]
            y4 = j + p[3][1]
            x = [x1, x2, x3, x4]
            y = [y1, y2, y3, y4]

            count = 0
            answer = 0
            for q in range(4):
                if 0 <= x[q] < row and 0 <= y[q] < column:
                    count += 1

            if count == 4:
                tmp = game[x1][y1] + game[x2][y2] + game[x3][y3] + game[x4][y4]
                result.append(tmp)

print(max(result))