from collections import deque

T = int(input())


def isOK(a, b, lens):
    return 0 <= a < lens and 0 <= b < lens


def move(width, lis, idx, count, target_a, target_b):
    dir_a = [2, 1, 2, 1, -2, -1, -2, -1]
    dir_b = [1, 2, -1, -2, -1, -2, 1, 2]

    while lis:
        target = lis.popleft()
        count += 1
        container = []

        for item in target:
            idx[item[0]][item[1]] = 1

            for case in range(8):
                da = item[0] + dir_a[case]
                db = item[1] + dir_b[case]

                if da == target_a and db == target_b:
                    return count

                else:
                    if isOK(da, db, width) and index[da][db] == 0:
                        index[da][db] = 1
                        container.append((da, db))

        if len(container) == 0:
            return 0
        else:
            queue.append(container)


sheet = []

for i in range(T):
    # 한 변의 길이
    length = int(input())
    # 시작 칸
    start_a, start_b = map(int, input().split())
    # 목적지 칸
    end_a, end_b = map(int, input().split())

    if start_a == end_a and start_b == end_b:
        sheet.append(0)
    else:
        queue = deque([[(start_a, start_b)]])
        index = [[0] * length for _ in range(length)]

        answer = move(length, queue, index, 0, end_a, end_b)
        sheet.append(answer)

for i in sheet:
    print(i)




