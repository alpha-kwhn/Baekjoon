from collections import deque
import copy
import math

N, L, R = map(int, input().split())
country = []
rest = []


def isOK(a, b, l):
    return 0 <= a < l and 0 <= b < l


def canMove(a, b):
    return L <= abs(a-b) <= R


def remake(land, targets):
    lis = []

    for items in targets:
        value = 0
        for things in items:
            value += things[0]
        value = math.floor(value / len(items))
        lis.append(value)

    for idx in range(len(targets)):
        for things in targets[idx]:
            land[things[1][0]][things[1][1]] = lis[idx]

    return land


for i in range(N):
    target = list(map(int, input().split()))
    for j in range(N):
        rest.append((i, j))
    country.append(target)

answer = 0

while True:
    dir_x = [0, 0, 1, -1]
    dir_y = [1, -1, 0, 0]

    container = []
    total = 0

    visited = [[0] * N for _ in range(N)]
    rest_copy = copy.deepcopy(rest)
    queue = deque([[rest_copy[0]]])

    basket = []

    while queue:
        tmp = queue.popleft()
        found = []

        for item in tmp:

            if (item[0], item[1]) in rest_copy:
                rest_copy.remove((item[0], item[1]))

            for case in range(4):
                dx = item[0] + dir_x[case]
                dy = item[1] + dir_y[case]

                if isOK(dx, dy, N) and visited[dx][dy] == 0:
                    if canMove(country[item[0]][item[1]], country[dx][dy]):
                        visited[dx][dy] = 1
                        found.append((dx, dy))
                        basket.append((country[dx][dy], (dx, dy)))

        if len(found) != 0:
            queue.append(found)
        else:
            if len(basket) > 0:
                container.append(basket)
                basket = []
            if len(rest_copy) > 0:
                queue.append([rest_copy[0]])
            else:
                break

    if len(container) == 0:
        break
    else:
        country = remake(country, container)
        answer += 1

print(answer)









