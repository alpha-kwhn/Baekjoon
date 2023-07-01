from collections import deque
from itertools import combinations

room = []
student = []
answer = 0


def isOK(a, b):
    return 0 <= a < 5 and 0 <= b < 5


def BFS(queue):
    index = [0] * 7
    lis = deque([queue[0]])
    index[0] = 1

    dir_x = [0, 0, 1, -1]
    dir_y = [1, -1, 0, 0]

    while lis:
        a, b = lis.popleft()

        for case in range(4):
            dx = a + dir_x[case]
            dy = b + dir_y[case]

            if isOK(dx, dy) and (dx, dy) in queue:
                if index[queue.index((dx, dy))] == 0:
                    lis.append((dx, dy))
                    index[queue.index((dx, dy))] = 1

    if 0 not in index:
        return True
    else:
        return False


# 교실 배치 입력
for i in range(5):
    row = list(str(input()))
    room.append(row)


# 학생들 좌표 전부 저장
for i in range(5):
    for j in range(5):
        student.append((i, j))

combination = list(combinations(student, 7))

for i in range(len(combination)):
    sentence = ""
    diction = {}
    for idx in range(7):
        sentence += room[combination[i][idx][0]][combination[i][idx][1]]
        diction[(combination[i][idx][0], combination[i][idx][1])] = 0

    if sentence.count("S") >= 4:
        if BFS(combination[i]):
            answer += 1

print(answer)
