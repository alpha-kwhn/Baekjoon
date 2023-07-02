from collections import deque

people = int(input())
diction = {}

while True:
    a, b = map(int, input().split())

    if a == -1 and b == -1:
        break
    else:
        if a not in diction:
            diction[a] = [b]
        else:
            diction[a].append(b)

        if b not in diction:
            diction[b] = [a]
        else:
            diction[b].append(a)

score = 0
president = []


def BFS(queue, start):
    index = [0] * people
    count = -1
    while queue:

        target = queue.popleft()
        container = []

        for item in target:
            for idx in item:
                if idx not in diction.keys():
                    return -1
                else:
                    if index[idx-1] == 0:
                        index[idx-1] = 1
                        container.append(diction[idx])

        if len(container) == 0:
            if 0 not in index:
                return count
            else:
                return -1
        else:
            queue.append(container)
            count += 1


result = [0] * people

for i in range(1, people+1):
    answer = BFS(deque([[[i]]]), i)
    if answer == -1:
        continue
    else:
        result[i-1] = answer

point = min(result)
idx = []

for i in range(people):
    if result[i] == point:
        idx.append(i+1)

print(point, result.count(point))

for item in idx:
    print(item, end=' ')
