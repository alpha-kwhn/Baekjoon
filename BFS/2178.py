from collections import deque

N, M = map(int, input().split())
maze = []


def isOK(a, b):
    return 0 <= a < N and 0 <= b < M


for _ in range(N):
    row = str(input())
    maze.append(list(row))

queue = deque([[(0, 0)]])
index = [[0] * M for _ in range(N)]
index[0][0] = 1


def BFS():
    count = 1

    dir_x = [0, 0, 1, -1]
    dir_y = [1, -1, 0, 0]

    while queue:
        target = queue.popleft()
        container = []

        for item in target:
            for case in range(4):
                dx = item[0] + dir_x[case]
                dy = item[1] + dir_y[case]

                if dx == N - 1 and dy == M - 1:
                    return count + 1

                if not isOK(dx, dy):
                    continue

                if maze[dx][dy] == "0":
                    continue

                if index[dx][dy] == 1:
                    continue

                index[dx][dy] = 1
                container.append((dx, dy))

        if len(container) != 0:
            queue.append(container)
            count += 1


print(BFS())
