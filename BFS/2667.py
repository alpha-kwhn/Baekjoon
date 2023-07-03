from collections import deque

N = int(input())
maze = []
house = []
index = [[0] * N for _ in range(N)]


def isOK(a, b):
    return 0 <= a < N and 0 <= b < N


for i in range(N):
    row = list(str(input()))
    maze.append(row)

    for item in range(N):
        if row[item] == "1":
            house.append((i, item))

queue = deque([[house.pop(0)]])


def BFS():
    dir_x = [0, 0, 1, -1]
    dir_y = [1, -1, 0, 0]
    count = 1
    answer = []

    while queue:
        target = queue.popleft()
        container = []

        for itm in target:
            for case in range(4):
                dx = itm[0] + dir_x[case]
                dy = itm[1] + dir_y[case]

                if isOK(dx, dy) and maze[dx][dy] == "1":
                    if (dx, dy) in house:
                        container.append((dx, dy))
                        count += 1
                        house.remove((dx, dy))

        if len(container) == 0:
            answer.append(count)
            count = 1

            if len(house) > 0:
                queue.append([house.pop(0)])
            else:
                return answer

        else:
            queue.append(container)


result = BFS()
print(len(result))
result.sort()

for i in result:
    print(i)
