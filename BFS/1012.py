from collections import deque


def isOk(a, b, w, h):
    return 0 <= a < w and 0 <= b < h


def BFS(a, b, lis, target, count):
    queue = deque([[target.pop(0)]])

    while queue:
        tmp = queue.popleft()
        container = []

        dir_x = [0, 0, 1, -1]
        dir_y = [1, -1, 0, 0]

        for item in tmp:
            index[item[0]][item[1]] = 1

            for case in range(4):
                dx = item[0] + dir_x[case]
                dy = item[1] + dir_y[case]

                if isOk(dx, dy, a, b) and index[dx][dy] == 0 and lis[dx][dy] == 1:
                    target.remove((dx, dy))
                    index[dx][dy] = 1
                    container.append((dx, dy))

        if len(container) > 0:
            queue.append(container)
        else:
            count += 1
            if len(target) == 0:
                return count
            else:
                queue = deque([[target.pop(0)]])


T = int(input())

answer = []

for i in range(T):
    width, height, num = map(int, input().split())
    farm = [[0] * height for _ in range(width)]
    index = [[0] * height for _ in range(width)]
    cabbage = []
    bugs = 0

    for k in range(num):
        x, y = map(int, input().split())
        farm[x][y] = 1
        cabbage.append((x, y))

    bugs = BFS(width, height, farm, cabbage, bugs)
    answer.append(bugs)


for i in range(T):
    print(answer[i])










