from collections import deque

N = int(input())
first, second = map(int, input().split())
relation = int(input())
diction = {}
path = [0] * N

# [[a]]


def find_child(queue, target, count):
    while queue:
        tmp = queue.popleft()
        container = []

        for arr in tmp:
            for item in arr:
                path[item[0]-1] = 1
                if item[0] == target:
                    return item[1]
                else:
                    if item[0] in diction:
                        lis = []
                        for k in diction[item[0]]:
                            if path[k-1] == 0:
                                path[k-1] = 1
                                lis.append((k, item[1] + 1))
                        container.append(lis)

        if len(container) == 0:
            return -1
        else:
            queue.append(container)


# 찾고자 하는 놈이 key에 없을때 적용되는 함수
def find_cousin(queue, target, count):
    while queue:
        tmp = queue.popleft()
        container = []

        for arr in tmp:
            for item in arr:
                path[item[0]-1] = 1
                if item[0] == target:
                    return item[1]
                else:
                    # 윗 관계 검사
                    for k in diction.keys():
                        if item[0] in diction[k]:
                            if path[k-1] == 0:
                                container.append([(k, item[1] + 1)])

                            if target in diction[k]:
                                return item[1] + 2
                            else:
                                lis = []
                                for p in diction[k]:
                                    if path[p-1] == 0:
                                        path[p-1] = 1
                                        lis.append((p, item[1] + 2))
                                container.append(lis)
                    # 아랫 관계 검사
                    if item[0] in diction:
                        test = find_child(deque([[[(item[0], item[1])]]]), target, 0)
                        if test != -1:
                            return test
        if len(container) == 0:
            return -1
        else:
            queue.append(container)


answer = 0

for _ in range(relation):
    a, b = map(int, input().split())

    if a not in diction:
        diction[a] = [b]
    else:
        diction[a].append(b)

if first in diction.keys():
    answer = find_child(deque([[[(first, 0)]]]), second, 0)
    if answer == -1:
        answer = find_cousin(deque([[[(first, 0)]]]), second, 0)
        print(answer)
    else:
        print(answer)
else:
    answer = find_cousin(deque([[[(first, 0)]]]), second, 0)
    print(answer)