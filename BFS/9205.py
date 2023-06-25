from collections import deque

T = int(input())


def check(x, y, dx, dy):
    return abs(dx - x) + abs(dy - y) <= 1000


def distance(x, y, dx, dy):
    return abs(x - dx) + abs(y - dy)


def BFS(store, dest_x, dest_y, home_x, home_y, dictionary):

    # 편의점이 없는 경우
    if len(store) == 0:
        if check(home_x, home_y, dest_x, dest_y):
            return "happy"
        else:
            return "sad"

    # 편의점이 있는 경우
    else:
        queue = deque([[(home_x, home_y, 20)]])

        while queue:
            tmp = queue.popleft()
            container = []

            for point in tmp:
                now_x = point[0]
                now_y = point[1]
                beer = point[2]

                # 현 위치에서 페스티벌장 까지의 거리가 500미터 이내인지 확인 (500미터 이내이면 편의점 안가고도 도착 가능)
                if check(now_x, now_y, dest_x, dest_y):
                    return "happy"

                # 주위 편의점을 찾아야 함
                else:
                    for item in store:
                        if beer >= (distance(now_x, now_y, item[0], item[1]) // 50) and dictionary[(item[0], item[1])] == 0:
                            # 맥주 20개로 재충전 & 시작점 재설정 & 편의점 찾았음을 표시
                            dictionary[(item[0], item[1])] = 1
                            container.append((item[0], item[1], 20))
                        else:
                            continue

            if len(container) == 0:
                return "sad"
            else:
                queue.append(container)


answer = []

# 50미터당 2병 마심, 맥주 최대 용량 20
for i in range(T):
    num = int(input())
    house_x, house_y = map(int, input().split())
    cvs = []
    diction = {}

    for j in range(num):
        a, b = map(int, input().split())
        cvs.append((a, b))
        diction[(a, b)] = 0

    fest_x, fest_y = map(int, input().split())

    target = BFS(cvs, fest_x, fest_y, house_x, house_y, diction)

    answer.append(target)

for i in answer:
    print(i)



