from collections import deque

N = int(input())
maze = []

mini = 0
maxi = 0


def isOK(a, b, width):
    return 0 <= a < width and 0 <= b < width


def search(land, safe, count):
    dir_x = [0, 0, 1, -1]
    dir_y = [1, -1, 0, 0]

    queue = deque([[safe.pop(0)]])

    while queue:
        tmp = queue.popleft()
        container = []

        for item in tmp:
            land[item[0]][item[1]] = 1

            for case in range(4):
                dx = item[0] + dir_x[case]
                dy = item[1] + dir_y[case]

                if isOK(dx, dy, N) and index[dx][dy] == 0:
                    index[dx][dy] = 1
                    container.append((dx, dy))

                    if (dx, dy) in safe:
                        safe.remove((dx, dy))

        if len(container) > 0:
            queue.append(container)
        else:
            count += 1
            if len(safe) != 0:
                new = safe.pop(0)
                queue.append([new])
            else:
                return count


for i in range(N):
    target = list(map(int, input().split()))

    if mini == 0:
        mini = min(target)
    else:
        if mini > min(target):
            mini = min(target)

    if maxi == 0:
        maxi = max(target)
    else:
        if maxi < max(target):
            maxi = max(target)

    maze.append(target)

answer = []


if mini == maxi:
    print(1)

else:
    for i in range(mini, maxi):
        index = [[0] * N for _ in range(N)]
        safety = []

        for idx in range(len(index)):
            for k in range(len(index[idx])):
                if maze[idx][k] <= i:
                    index[idx][k] = 1
                else:
                    safety.append((idx, k))

        answer.append(search(index, safety, 0))

    print(max(answer))



