from collections import deque

N, M = map(int, input().split())

diction = {}
path = [0] * N
network = 0
alone = [0] * N

# 간선관계 입력
for i in range(M):
    first, second = map(int, input().split())
    alone[first-1] = 1
    alone[second-1] = 1

    if first in diction:
        diction[first].append(second)
    else:
        diction[first] = [second]

    if second in diction:
        diction[second].append(first)
    else:
        diction[second] = [first]

network = alone.count(0)


# BFS 함수
def BFS(queue, count):
    while queue:
        tmp = queue.popleft()
        container = []

        for item in tmp:
            for lis in item[0]:
                if lis != item[1] and path[lis - 1] == 0:
                    if lis in diction:
                        container.append((diction[lis], lis))
                    path[lis - 1] = 1
                elif lis != item[1] and path[lis - 1] == 1:
                    continue
                elif lis == item[1]:
                    continue

        if len(container) != 0:
            queue.append(container)
        else:
            count += 1
            return count


for i in range(1, N+1):
    if i in diction and path[i-1] == 0:
        path[i - 1] = 1
        target = deque([[(diction[i], i)]])
        network = BFS(target, network)
    else:
        continue

print(network)

