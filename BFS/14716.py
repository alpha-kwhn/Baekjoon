from collections import deque

M, N = map(int, input().split())
text = []
index = [[0] * N for _ in range(M)]
maze = []


def isOK(a, b):
    return 0 <= a < M and 0 <= b < N


for i in range(M):
    target = list(map(int, input().split()))

    for item in range(N):
        if target[item] == 1:
            text.append((i, item))

    maze.append(target)

if len(text) == 0:
    print(0)
else:
    queue = deque([[text[0]]])
    dir_x = [0, 0, 1, -1, 1, -1, 1, -1]
    dir_y = [1, -1, 0, 0, 1, 1, -1, -1]
    count = 0

    while queue:
        target = queue.popleft()
        container = []

        for itm in target:
            index[itm[0]][itm[1]] = 1
            text.remove(itm)

            for case in range(8):
                dx = itm[0] + dir_x[case]
                dy = itm[1] + dir_y[case]

                if isOK(dx, dy) and index[dx][dy] == 0:
                    index[dx][dy] = 1
                    if maze[dx][dy] == 1:
                        container.append((dx, dy))

        if len(container) == 0:
            count += 1
            if len(text) > 0:
                queue.append([text[0]])
        else:
            queue.append(container)

    print(count)