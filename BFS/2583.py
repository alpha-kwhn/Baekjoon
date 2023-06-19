from collections import deque

M, N, K = map(int, input().split())

index = [[0] * N for _ in range(M)]
area = []
total = []

for i in range(K):
    first_x, first_y, second_x, second_y = map(int, input().split())

    first_y = M - first_y
    second_y = M - second_y

    mini = min(first_y, second_y)
    maxi = max(first_y, second_y)

    for idx in range(mini, maxi):
        for ids in range(first_x, second_x):
            index[idx][ids] = 1


def isOK(a, b, w, h):
    return 0 <= a < h and 0 <= b < w


def BFS(queue, space):
    while queue:
        target = queue.popleft()
        container = []

        dir_x = [0, 0, 1, -1]
        dir_y = [1, -1, 0, 0]

        for item in target:
            index[item[0]][item[1]] = 1
            for case in range(4):
                dx = item[0] + dir_x[case]
                dy = item[1] + dir_y[case]

                if isOK(dx, dy, N, M) and index[dx][dy] == 0:
                    index[dx][dy] = 1
                    container.append((dx, dy))
                    space += 1

        if len(container) == 0:
            return space
        else:
            queue.append(container)


for row in range(len(index)):
    for col in range(len(index[row])):
        if index[row][col] == 0:
            total.append(BFS(deque([[(row, col)]]), 1))
        else:
            continue

total.sort()

print(len(total))
print(' '.join(map(str, total)))
