gear = []
cmd = []
answer = 0

# 0-N, 1-S
for i in range(4):
    tmp = str(input())
    target = list(map(int, tmp))
    gear.append(target)

K = int(input())

# 1: 시계, -1: 반시계
for i in range(K):
    index, direction = map(int, input().split())
    cmd.append([index, direction])

for i in cmd:
    # 톱니들이 회전될방향
    move = [0, 0, 0, 0]

    # 1번 톱니회전
    if i[0] == 1:
        # 시계회전
        if i[1] == 1:
            move[0] = 1
        elif i[1] == -1:
            move[0] = -1

        for k in range(3):
            if gear[k][2] != gear[k+1][-2]:
                move[k+1] = (move[k] * -1)
            else:
                move = move[:k+1] + [0] * (3 - k)
                break

    # 2번 톱니회전
    elif i[0] == 2:
        # 시계회전
        if i[1] == 1:
            move[1] = 1
        elif i[1] == -1:
            move[1] = -1

        # 1번 톱니 회전 방향 결정
        if gear[0][2] != gear[1][-2]:
            move[0] = (move[1] * -1)

        # 3, 4번 톱니 회전 방향 결정
        for k in range(1, 3):
            if gear[k][2] != gear[k+1][-2]:
                move[k+1] = (move[k] * -1)
            else:
                move = move[:k+1] + [0] * (3 - k)

    # 3번 톱니회전
    elif i[0] == 3:
        # 시계회전
        if i[1] == 1:
            move[2] = 1
        elif i[1] == -1:
            move[2] = -1

        # 4번 톱니 회전 방향 결정
        if gear[2][2] != gear[3][-2]:
            move[3] = (move[2] * -1)

        # 1, 2번 톱니 회전 방향 결정
        for k in range(2, 0, -1):
            if gear[k][-2] != gear[k-1][2]:
                move[k-1] = (move[k] * -1)
            else:
                move = [0] * k + move[k:]

    # 4번 톱니회전
    elif i[0] == 4:
        # 시계회전
        if i[1] == 1:
            move[3] = 1
        elif i[1] == -1:
            move[3] = -1

        for k in range(3, 0, -1):
            if gear[k][-2] != gear[k-1][2]:
                move[k-1] = (move[k] * -1)
            else:
                move = [0] * k + move[k:]
                break

    container = []

    for p in range(len(move)):
        if move[p] == 0:
            container.append(gear[p])
        # 시계회전
        elif move[p] == 1:
            tmp = gear[p].pop(-1)
            container.append([tmp] + gear[p])
        # 반시계회전
        elif move[p] == -1:
            tmp = gear[p].pop(0)
            container.append(gear[p] + [tmp])
    gear = container


if gear[0][0] == 1:
    answer += 1
if gear[1][0] == 1:
    answer += 2
if gear[2][0] == 1:
    answer += 4
if gear[3][0] == 1:
    answer += 8

print(answer)
