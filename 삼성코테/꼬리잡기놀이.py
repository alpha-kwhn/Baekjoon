# 1. 각 팀은 머리사람을 따라서 한 칸 이동합니다.
# 2. 각 라운드마다 공이 정해진 선을 따라 던져집니다 (1라운드 첫번쨰줄..)
# (우, 상, 좌, 하) 방향으로 0~n까지 공이 날라감
# 3. 공이 던져질 경우에 해당 선에 사람이 있으면 최초에 만나게 되는 사람만이 점수 획득
# (머리사람을 시작으로 팀 내에서 k번째 사람이라면 k의 제곱만큼 점수 획득)
# (아무도 공 못받으면 점수 미획득)
# 공을 획득하면 머리사람과 꼬리사람이 바뀐다 (이동방향 변경)
# 각 팀이 획득한 점수의 총 합 구하기
from collections import deque
# 격자 크기, 팀의 개수, 라운드 수
n, m, k = map(int, input().split())

teams = []
points = [0] * m
maze = []
clock = [1] * m
lengths = [0] * m
champion = -1

for i in range(n):
    row = list(map(int, input().split()))

    for j in range(len(row)):
        # 1이 있다면 위치 저장
        if row[j] == 1:
            # 인덱스, 좌표, 순서
            teams.append([[1, i, j, 1]])
    maze.append(row)


# 좌표의 타당성 판단
def isOK(a, b, height, width):
    return 0 <= a < height and 0 <= b < width


# 1의 위치를 기준으로 탐색 시작 방향을 파악
def team(start_x, start_y, teams, n, maps, size, index, count, lengths):
    dir_x = [0, -1, 1, 0]
    dir_y = [-1, 0, 0, 1]
    direction = 0

    index[start_x][start_y] = 1

    # 탐색 시작 방향 정하기
    for p in range(4):
        nx = start_x + dir_x[p]
        ny = start_y + dir_y[p]

        if isOK(nx, ny, size, size) and index[nx][ny] == 0 and 0 < maps[nx][ny] < 4:
            # 뒷 팀원 발견하면 이동선 좌표 목록에 추가
            if maps[nx][ny] == 2:
                teams[n].append([maps[nx][ny], nx, ny, count])
                count += 1
                index[nx][ny] = 1
                direction = p
            elif maps[nx][ny] == 3:
                teams[n].append([maps[nx][ny], nx, ny, count])
                lengths[n] = count
                index[nx][ny] = 1
                direction = p
            break
        else:
            continue
    # DFS 진행
    DFS((start_x + dir_x[direction], start_y + dir_y[direction]), direction, teams, n, maps, size, index, count, lengths)

    return teams, lengths[n]


# DFS 함수 - 시작점, 방향, 팀인덱스,
def DFS(start, direction, teams, n, maps, size, index, count, lengths):
    dir_x = [0, -1, 1, 0]
    dir_y = [-1, 0, 0, 1]
    x, y = start

    flag = 0

    nx = x + dir_x[direction]
    ny = y + dir_y[direction]

    if isOK(nx, ny, size, size) and 0 < maps[nx][ny] <= 4 and index[nx][ny] == 0:
        if maps[nx][ny] == 2:
            teams[n].append([maps[nx][ny], nx, ny, count])
            count += 1
            index[nx][ny] = 1
            DFS((nx, ny), direction, teams, n, maps, size, index, count, lengths)
        elif maps[nx][ny] == 3:
            teams[n].append([maps[nx][ny], nx, ny, count])
            index[nx][ny] = 1
            lengths[n] = count
            DFS((nx, ny), direction, teams, n, maps, size, index, count, lengths)
        elif maps[nx][ny] == 4:
            teams[n].append([maps[nx][ny], nx, ny, -1])
            index[nx][ny] = 1
            DFS((nx, ny), direction, teams, n, maps, size, index, count, lengths)
    else:
        # 탐색 시작 방향 정하기
        for p in range(4):
            if p == direction:
                continue
            else:
                nx = x + dir_x[p]
                ny = y + dir_y[p]

                if isOK(nx, ny, size, size) and index[nx][ny] == 0 and 0 < maps[nx][ny] <= 4:
                    if maps[nx][ny] == 2:
                        teams[n].append([maps[nx][ny], nx, ny, count])
                        count += 1
                        index[nx][ny] = 1
                        direction = p
                        flag = 1
                        break
                    elif maps[nx][ny] == 3:
                        teams[n].append([maps[nx][ny], nx, ny, count])
                        lengths[n] = count
                        index[nx][ny] = 1
                        direction = p
                        flag = 1
                        break
                    elif maps[nx][ny] == 4:
                        teams[n].append([maps[nx][ny], nx, ny, -1])
                        index[nx][ny] = 1
                        direction = p
                        flag = 1
                        break
        if flag == 1:
            DFS((nx, ny), direction, teams, n, maps, size, index, count, lengths)


# 팀원들 좌표 파악
for i in range(m):
    count = 2
    index = [[0 for m in range(n)] for m in range(n)]
    _, x, y, _ = teams[i][0]
    teams, cnt = team(x, y, teams, i, maze, n, index, count, lengths)

# 놀이 진행
for ball in range(k):
    # 이전에 공에 맞은 팀이 존재한다면
    if champion != -1:
        # 이동방향을 변경
        clock[champion] *= -1

    # 1. 한 칸씩 이동
    for i in range(m):
        if clock[i] == 1:
            tmp = teams[i][0][0]
            rank = teams[i][0][3]
            for j in range(1, len(teams[i])):
                teams[i][j-1][0] = teams[i][j][0]
                teams[i][j-1][3] = teams[i][j][3]
            teams[i][-1][0] = tmp
            teams[i][-1][3] = rank
        elif clock[i] == -1:
            tmp = teams[i][-1][0]
            rank = teams[i][-1][3]
            for j in range(len(teams[i]) - 2, -1, -1):
                teams[i][j+1][0] = teams[i][j][0]
                teams[i][j+1][3] = teams[i][j][3]
            teams[i][0][0] = tmp
            teams[i][0][3] = rank
    basket = []
    # 인덱스, x, y, 순서 + 팀번호

    # 2. 공이 던져짐
    if (ball // n) % 4 == 0:
        for i in range(m):
            for j in range(len(teams[i])):
                if teams[i][j][1] == (ball % n) and 0 < teams[i][j][0] < 4:
                    tmp = teams[i][j] + [i]
                    basket.append(tmp)
        if len(basket) > 0:
            basket.sort(key = lambda x:x[2])
            winner = basket[0]
            points[winner[4]] += winner[3] ** 2
            champion = winner[4]

    elif (ball // n) % 4 == 1:
        for i in range(m):
            for j in range(len(teams[i])):
                if teams[i][j][2] == (ball % n) and 0 < teams[i][j][0] < 4:
                    tmp = teams[i][j] + [i]
                    basket.append(tmp)
        if len(basket) > 0:
            basket.sort(key = lambda x:-x[1])
            winner = basket[0]
            points[winner[4]] += winner[3] ** 2
            champion = winner[4]

    elif (ball // n) % 4 == 2:
        for i in range(m):
            for j in range(len(teams[i])):
                if teams[i][j][1] == ((n - 1) - (ball % n)) and 0 < teams[i][j][0] < 4:
                    tmp = teams[i][j] + [i]
                    basket.append(tmp)
        if len(basket) > 0:
            basket.sort(key = lambda x: -x[2])
            winner = basket[0]
            points[winner[4]] += winner[3] ** 2
            champion = winner[4]

    elif (ball // n) % 4 == 3:
        for i in range(m):
            for j in range(len(teams[i])):
                if teams[i][j][2] == ((n - 1) - (ball % n)) and 0 < teams[i][j][0] < 4:
                    tmp = teams[i][j] + [i]
                    basket.append(tmp)
        if len(basket) > 0:
            basket.sort(key = lambda x: x[1])
            winner = basket[0]
            points[winner[4]] += winner[3] ** 2
            champion = winner[4]

    # 공에 맞은 팀이 있는 경우
    if len(basket) > 0:
        n_tail = -1
        n_head = -1
        distance = lengths[champion]
        middle = -1
        method = 0

        # 중앙값 구하기
        if distance % 2 == 1:
            middle = (distance // 2) + 1
            method = 0
        elif distance % 2 == 0:
            middle = (distance // 2)
            method = 1

        # 3. 점수 획득 팀은 머리 꼬리가 바뀜
        for o in range(len(teams[champion])):
            # 3.1 사람 순서 역순으로 바꿔주기
            if teams[champion][o][3] != -1:
                number = teams[champion][o][3]
                if method == 0:
                    teams[champion][o][3] = (2 * middle) - number
                elif method == 1:
                    teams[champion][o][3] = ((2 * middle) - number) + 1

            # 3.2 머리와 꼬리의 현 좌표를 임시저장
            if teams[champion][o][0] == 1:
                n_tail = o
            elif teams[champion][o][0] == 3:
                n_head = o
        # 3.3 머리와 꼬리를 바꿔줌
        teams[champion][n_tail][0] = 3
        teams[champion][n_head][0] = 1
print(sum(points))




