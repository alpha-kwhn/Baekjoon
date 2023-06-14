from collections import deque

x, y = map(int, input().split())
tomato = []


# 좌표 값 타당 여부 검토
def isOk(a, b, row, col):
    return 0 <= a < col and 0 <= b < row


# 토마토 박스 구성
def init(fresh):
    first = []

    for j in range(y):
        row = list(map(int, input().split()))
        for k in range(len(row)):
            if row[k] != 0:
                fresh += 1
                if row[k] == 1:
                    first.append((j, k))
        tomato.append(row)
    queue = deque([first])
    return queue, fresh


# x, y, 층
index = [[0] * x for _ in range(y)]

dir_x = [0, 0, 1, -1]
dir_y = [1, -1, 0, 0]


# 주위에 토마토 없는 칸이 있는지 탐색
def search(lis, fresh, day):
    while lis:
        target = lis.popleft()
        container = []

        day += 1
        for item in target:
            index[item[0]][item[1]] = 1

            for t in range(4):
                dx = item[0] + dir_x[t]
                dy = item[1] + dir_y[t]

                if isOk(dx, dy, x, y) and index[dx][dy] == 0 and tomato[dx][dy] == 0:
                    index[dx][dy] = 1
                    tomato[dx][dy] = 1
                    fresh += 1
                    container.append((dx, dy))

        if len(container) == 0 and fresh < (x*y):
            return -1
        elif len(container) == 0 and fresh == (x*y):
            return day - 1
        elif len(container) != 0:
            lis.append(container)


que, count = init(0)
answer = search(que, count, 0)
print(answer)