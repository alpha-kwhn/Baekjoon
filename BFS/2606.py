from collections import deque

computer = int(input())
network = int(input())
diction = {}
path = [0] * computer
diverse = [0] * computer


def BFS(lis, count):
    queue = deque([[lis]])

    while queue:
        tmp = queue.popleft()
        container = []

        for item in tmp:
            for j in item[0]:
                if j != 1 and path[j-1] == 0:
                    path[j-1] = 1
                    if j in diction:
                        container.append((diction[j], j))
                    count += 1
                elif j != 1 and path[j-1] != 0:
                    continue
                elif j == 1:
                    continue

        if len(container) != 0:
            queue.append(container)
        else:
            return count


def reverse(num, count):
    queue = deque([[num]])

    while queue:
        tmp = queue.popleft()
        container = []

        for item in tmp:
            for key in diction.keys():
                if item in diction[key] and path[key - 1] == 0:
                    container.append(key)
                    count += 1
                elif item in diction[key] and path[key - 1] != 0:
                    continue

        if len(container) == 0:
            return count
        else:
            queue.append(container)


for i in range(network):
    first, second = map(int, input().split())

    if first not in diction:
        diction[first] = [second]
    else:
        diction[first].append(second)

    if second not in diction:
        diction[second] = [first]
    else:
        diction[second].append(first)

if 1 in diction:
    target = diction[1]
    a = BFS((target, 1), 0)
    b = reverse(1, 0)
    print(a+b)
else:
    print(0)