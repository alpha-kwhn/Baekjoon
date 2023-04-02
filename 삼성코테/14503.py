from collections import deque


def isOK(a, b, height, width):
    return 0 <= a < height and 0 <= b < width


n, m = map(int, input().split())
robot_n, robot_m, direction = map(int, input().split())
maze = []

for i in range(n):
    row = list(map(int, input().split()))
    maze.append(row)

index = [[0] * m for _ in range(n)]
dir_x = [0, 1, 0, -1]
dir_y = [-1, 0, 1, 0]

queue = deque([[[robot_n, robot_m]]])
index[robot_n][robot_m] = 1
count = 1


def north(x, y):
    return maze[x + dir_y[0]][y + dir_x[0]] == 0 and index[x + dir_y[0]][y + dir_x[0]] == 0 and isOK(
            x + dir_y[0], y + dir_x[0], n, m)


while queue:
    target = queue.popleft()
    # [[]]
    # []
    container = []
    for i in target:
        # 사방의 청소여부/벽여부 확인

        if direction == 0:
            # 미청소, 미방문, 타당좌표

            if maze[i[0] + dir_y[3]][i[1] + dir_x[3]] == 0 and index[i[0] + dir_y[3]][i[1] + dir_x[3]] == 0 and isOK(
                    i[0] + dir_y[3], i[1] + dir_x[3], n, m):
                container.append([i[0] + dir_y[3], i[1] + dir_x[3]])
                index[i[0] + dir_y[3]][i[1] + dir_x[3]] = 1
                count += 1
                direction = 3

            elif maze[i[0] + dir_y[2]][i[1] + dir_x[2]] == 0 and index[i[0] + dir_y[2]][i[1] + dir_x[2]] == 0 and isOK(
                    i[0] + dir_y[2], i[1] + dir_x[2], n, m):
                container.append([i[0] + dir_y[2], i[1] + dir_x[2]])
                index[i[0] + dir_y[2]][i[1] + dir_x[2]] = 1
                count += 1
                direction = 2

            elif maze[i[0]+dir_y[1]][i[1]+dir_x[1]] == 0 and index[i[0]+dir_y[1]][i[1]+dir_x[1]] == 0 and isOK(i[0]+dir_y[1], i[1]+dir_x[1], n, m):
                container.append([i[0]+dir_y[1], i[1]+dir_x[1]])
                index[i[0]+dir_y[1]][i[1]+dir_x[1]] = 1
                count += 1
                direction = 1

            elif north(i[0], i[1]):
                container.append([i[0]+dir_y[0], i[1]+dir_x[0]])
                index[i[0]+dir_y[0]][i[1]+dir_x[0]] = 1
                count += 1
                direction = 0

        elif direction == 1:

            if north(i[0], i[1]):
                container.append([i[0]+dir_y[0], i[1]+dir_x[0]])
                index[i[0]+dir_y[0]][i[1]+dir_x[0]] = 1
                count += 1
                direction = 0

            elif maze[i[0] + dir_y[3]][i[1] + dir_x[3]] == 0 and index[i[0] + dir_y[3]][i[1] + dir_x[3]] == 0 and isOK(
                    i[0] + dir_y[3], i[1] + dir_x[3], n, m):
                container.append([i[0] + dir_y[3], i[1] + dir_x[3]])
                index[i[0] + dir_y[3]][i[1] + dir_x[3]] = 1
                count += 1
                direction = 3

            elif maze[i[0] + dir_y[2]][i[1] + dir_x[2]] == 0 and index[i[0] + dir_y[2]][i[1] + dir_x[2]] == 0 and isOK(
                    i[0] + dir_y[2], i[1] + dir_x[2], n, m):
                container.append([i[0] + dir_y[2], i[1] + dir_x[2]])
                index[i[0] + dir_y[2]][i[1] + dir_x[2]] = 1
                count += 1
                direction = 2

            elif maze[i[0] + dir_y[1]][i[1] + dir_x[1]] == 0 and index[i[0] + dir_y[1]][i[1] + dir_x[1]] == 0 and isOK(
                    i[0] + dir_y[1], i[1] + dir_x[1], n, m):
                container.append([i[0] + dir_y[1], i[1] + dir_x[1]])
                index[i[0] + dir_y[1]][i[1] + dir_x[1]] = 1
                count += 1
                direction = 1

        elif direction == 2:
            if maze[i[0] + dir_y[1]][i[1] + dir_x[1]] == 0 and index[i[0] + dir_y[1]][i[1] + dir_x[1]] == 0 and isOK(
                    i[0] + dir_y[1], i[1] + dir_x[1], n, m):
                container.append([i[0] + dir_y[1], i[1] + dir_x[1]])
                index[i[0] + dir_y[1]][i[1] + dir_x[1]] = 1
                count += 1
                direction = 1

            elif north(i[0], i[1]):
                container.append([i[0] + dir_y[0], i[1] + dir_x[0]])
                index[i[0] + dir_y[0]][i[1] + dir_x[0]] = 1
                count += 1
                direction = 0

            elif maze[i[0] + dir_y[3]][i[1] + dir_x[3]] == 0 and index[i[0] + dir_y[3]][i[1] + dir_x[3]] == 0 and isOK(
                    i[0] + dir_y[3], i[1] + dir_x[3], n, m):
                container.append([i[0] + dir_y[3], i[1] + dir_x[3]])
                index[i[0] + dir_y[3]][i[1] + dir_x[3]] = 1
                count += 1
                direction = 3

            elif maze[i[0] + dir_y[2]][i[1] + dir_x[2]] == 0 and index[i[0] + dir_y[2]][i[1] + dir_x[2]] == 0 and isOK(
                    i[0] + dir_y[2], i[1] + dir_x[2], n, m):
                container.append([i[0] + dir_y[2], i[1] + dir_x[2]])
                index[i[0] + dir_y[2]][i[1] + dir_x[2]] = 1
                count += 1
                direction = 2

        elif direction == 3:
            if maze[i[0] + dir_y[2]][i[1] + dir_x[2]] == 0 and index[i[0] + dir_y[2]][i[1] + dir_x[2]] == 0 and isOK(
                    i[0] + dir_y[2], i[1] + dir_x[2], n, m):
                container.append([i[0] + dir_y[2], i[1] + dir_x[2]])
                index[i[0] + dir_y[2]][i[1] + dir_x[2]] = 1
                count += 1
                direction = 2

            elif maze[i[0] + dir_y[1]][i[1] + dir_x[1]] == 0 and index[i[0] + dir_y[1]][i[1] + dir_x[1]] == 0 and isOK(
                    i[0] + dir_y[1], i[1] + dir_x[1], n, m):
                container.append([i[0] + dir_y[1], i[1] + dir_x[1]])
                index[i[0] + dir_y[1]][i[1] + dir_x[1]] = 1
                count += 1
                direction = 1

            elif north(i[0], i[1]):
                container.append([i[0] + dir_y[0], i[1] + dir_x[0]])
                index[i[0] + dir_y[0]][i[1] + dir_x[0]] = 1
                count += 1
                direction = 0

            elif maze[i[0] + dir_y[3]][i[1] + dir_x[3]] == 0 and index[i[0] + dir_y[3]][i[1] + dir_x[3]] == 0 and isOK(
                    i[0] + dir_y[3], i[1] + dir_x[3], n, m):
                container.append([i[0] + dir_y[3], i[1] + dir_x[3]])
                index[i[0] + dir_y[3]][i[1] + dir_x[3]] = 1
                count += 1
                direction = 3


        # 청소구역 못 찾는 경우
        if len(container) == 0:
            # 북쪽방향이면서 남쪽으로 이동했을때 벽이 아닌 경우
            if direction == 0:
                if maze[i[0]+dir_y[2]][i[1]+dir_x[2]] != 1 and isOK(i[0]+dir_y[2], i[1]+dir_x[2], n, m):
                    container.append([i[0]+dir_y[2], i[1]+dir_x[2]])
                    # 청소안했다면
                    if index[i[0]+dir_y[2]][i[1]+dir_x[2]] == 0:
                       count += 1
                       index[i[0] + dir_y[2]][i[1] + dir_x[2]] = 1
            # 동쪽방향이면서 서쪽으로 이동했을때
            elif direction == 1:
                # 벽이 아니고, 타당한 좌표값일 때
                if maze[i[0]+dir_y[3]][i[1]+dir_x[3]] != 1 and isOK(i[0]+dir_y[3], i[1]+dir_x[3], n, m):
                    container.append([i[0]+dir_y[3], i[1]+dir_x[3]])
                    # 청소안했다면
                    if index[i[0]+dir_y[3]][i[1]+dir_x[3]] == 0:
                       count += 1
                       index[i[0] + dir_y[3]][i[1] + dir_x[3]] = 1
            # 남쪽방향이면서 북쪽으로 이동했을 때
            elif direction == 2:
                # 벽이 아니고, 타당한 좌표값일 때
                if maze[i[0] + dir_y[0]][i[1] + dir_x[0]] != 1 and isOK(i[0] + dir_y[0], i[1] + dir_x[0], n, m):
                    container.append([i[0] + dir_y[0], i[1] + dir_x[0]])
                    # 청소안했다면
                    if index[i[0] + dir_y[0]][i[1] + dir_x[0]] == 0:
                        count += 1
                        index[i[0] + dir_y[0]][i[1] + dir_x[0]] = 1
            # 서쪽방향이면서 동쪽으로 이동했을 때
            elif direction == 3:
                # 벽이 아니고, 타당한 좌표값일 때
                if maze[i[0] + dir_y[1]][i[1] + dir_x[1]] != 1 and isOK(i[0] + dir_y[1], i[1] + dir_x[1], n, m):
                    container.append([i[0] + dir_y[1], i[1] + dir_x[1]])
                    # 청소안했다면
                    if index[i[0] + dir_y[1]][i[1] + dir_x[1]] == 0:
                        count += 1
                        index[i[0] + dir_y[1]][i[1] + dir_x[1]] = 1
    if len(container) != 0:
        queue.append(container)

print(count)








