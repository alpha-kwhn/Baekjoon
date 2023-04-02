N, L = map(int, input().split())
maze = []
count = 0

for i in range(N):
    row = list(map(int, input().split()))
    maze.append(row)

    tmp = list(set(row))

    # 길 높이가 모두 같은 경우
    if len(tmp) == 1:
        count += 1
    # 아닌경우
    else:
        steps = []
        start = []
        flag = 1

        for j in range(len(row)):
            if j == 0:
                start.append(row[j])
            else:
                if row[j] == row[j-1]:
                    start.append(row[j])
                else:
                    steps.append(start)
                    start = [row[j]]
        steps.append(start)

        build = [0 for _ in range(len(row))]
        point = 0

        for k in range(len(steps) - 1):
            if steps[k][0] + 1 == steps[k + 1][0] and len(steps[k]) >= L:
                history = point
                point += len(steps[k])
                if build[point - L:point] == [0] * L:
                    build = build[:point - L] + ([1] * L) + build[point:]
                    point = history + len(steps[k])
                    continue
                else:
                    flag = 0
                    break
            elif steps[k][0] - 1 == steps[k + 1][0] and len(steps[k + 1]) >= L:
                history = point
                point += (len(steps[k]) + L)
                if build[point - L:point] == [0] * L:
                    build = build[:point - L] + ([1] * L) + build[point:]
                    point = history + len(steps[k])
                    continue
                else:
                    flag = 0
                    break
            else:
                flag = 0
                break

        if flag == 1:
            count += 1


for i in range(N):
    container = []
    for j in range(N):
        container.append(maze[j][i])

    tmp = list(set(container))

    if len(tmp) == 1:
        count += 1
    else:
        steps = []
        start = []
        flag = 1

        for j in range(len(container)):
            if j == 0:
                start.append(container[j])
            else:
                if container[j] == container[j - 1]:
                    start.append(container[j])
                else:
                    steps.append(start)
                    start = [container[j]]
        steps.append(start)

        build = [0 for _ in range(len(container))]
        point = 0

        for k in range(len(steps) - 1):
            if steps[k][0] + 1 == steps[k + 1][0] and len(steps[k]) >= L:
                history = point
                point += len(steps[k])
                if build[point-L:point] == [0] * L:
                    build = build[:point-L] + ([1] * L) + build[point:]
                    point = history + len(steps[k])
                    continue
                else:
                    flag = 0
                    break
            elif steps[k][0] - 1 == steps[k + 1][0] and len(steps[k + 1]) >= L:
                history = point
                point += (len(steps[k]) + L)
                if build[point-L:point] == [0] * L:
                    build = build[:point-L] + ([1] * L) + build[point:]
                    point = history + len(steps[k])
                    continue
                else:
                    flag = 0
                    break
            else:
                flag = 0
                break

        if flag == 1:
            count += 1

print(count)










