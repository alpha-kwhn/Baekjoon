from collections import deque
from itertools import combinations
import copy


def isOK(a, b, width, height):
    return 0 <= a < height and 0 <= b < width


n, m = map(int, input().split())
maze = []

safety = []
wall = []
virus = []
answer = []

dir_x = [0, 0, 1, -1]
dir_y = [1, -1, 0, 0]

for i in range(n):
    row = list(map(int, input().split()))
    maze.append(row)

    for p in range(len(row)):
        if row[p] == 0:
            safety.append([i, p])
        elif row[p] == 1:
            wall.append([i, p])
        elif row[p] == 2:
            virus.append([i, p])

cases = list(combinations(safety, 3))

for i in range(len(cases)):
    # deep copy
    tmp = copy.deepcopy(maze)

    # 안전구역에 임의의 벽 배치
    for j in range(3):
        x = cases[i][j][0]
        y = cases[i][j][1]
        tmp[x][y] = 1

    # 바이러스 좌표 큐에 배치하기 [[[d,d],[d,d]]]
    queue = deque([virus])

    #안전영역 + 방문여부 체크 리스트
    count = len(safety) - 3
    index = [[0] * m for i in range(n)]

    # BFS
    while queue:

        # [[d,d], [d,d]]
        target = queue.popleft()
        container = []
        # t = [d,d]
        for t in target:
            # 좌표 타당성, 방문여부, 값이 0인지 여부
            if isOK(t[0]+dir_y[0], t[1]+dir_x[0], m, n) and index[t[0]+dir_y[0]][t[1]+dir_x[0]] == 0 and tmp[t[0]+dir_y[0]][t[1]+dir_x[0]] == 0:
                container.append([t[0]+dir_y[0], t[1]+dir_x[0]])
                index[t[0] + dir_y[0]][t[1] + dir_x[0]] = 1
                count -= 1
            if isOK(t[0]+dir_y[1], t[1]+dir_x[1], m, n) and index[t[0]+dir_y[1]][t[1]+dir_x[1]] == 0 and tmp[t[0]+dir_y[1]][t[1]+dir_x[1]] == 0:
                container.append([t[0] + dir_y[1], t[1] + dir_x[1]])
                index[t[0] + dir_y[1]][t[1] + dir_x[1]] = 1
                count -= 1
            if isOK(t[0]+dir_y[2], t[1]+dir_x[2], m, n) and index[t[0]+dir_y[2]][t[1]+dir_x[2]] == 0 and tmp[t[0]+dir_y[2]][t[1]+dir_x[2]] == 0:
                container.append([t[0] + dir_y[2], t[1] + dir_x[2]])
                index[t[0] + dir_y[2]][t[1] + dir_x[2]] = 1
                count -= 1
            if isOK(t[0]+dir_y[3], t[1]+dir_x[3], m, n) and index[t[0]+dir_y[3]][t[1]+dir_x[3]] == 0 and tmp[t[0]+dir_y[3]][t[1]+dir_x[3]] == 0:
                container.append([t[0] + dir_y[3], t[1] + dir_x[3]])
                index[t[0] + dir_y[3]][t[1] + dir_x[3]] = 1
                count -= 1

        if len(container) != 0:
            queue.append(container)

    # BFS 종료 -> 안전 영역의 크기를 answer 리스트에 넣어준다
    answer.append(count)

print(max(answer))
