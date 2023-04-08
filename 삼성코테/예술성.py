import copy

n = int(input())
maze = []

# 그림 형성
for i in range(n):
    row = list(map(int, input().split()))
    maze.append(row)

answer = []


# 좌표 타당성
def isOK(a, b, height, width):
    return 0 <= a < height and 0 <= b < width


# 그룹을 나눠주는 함수
def DFS(x, y, team, size, picture, index, count):
    dir_x = [0, -1, 0, 1]
    dir_y = [1, 0, -1, 0]

    # 현 좌표는 방문했음을 표시
    index[x][y] = 1

    # 현 좌표 값을 바탕으로 그룹 저장
    # 팀 숫자, 팀에 속한 좌표 값들의 모음, 맞닿은 다른 그룹 좌표값 모음
    # team[count] = (picture[x][y], [(x, y)], [])

    # 상하좌우 탐색 진행
    for p in range(4):
        # 예상 목적좌표
        nx = x + dir_x[p]
        ny = y + dir_y[p]

        # 1. 좌표가 격자를 벗어날 때
        if not isOK(nx, ny, size, size):
            continue

        # 2. 목적지 좌표 값이 현 좌표 값과 다를 경우 (새로운 그룹의 발견)
        if picture[x][y] != picture[nx][ny]:
            # 2.1 인접좌표 리스트에 추가
            team[count][3].append((nx, ny))
            # 2.2 방향전환
            continue

        # 3. 목적지 좌표 값이 현 좌표 값과 같을 경우 (같은 그룹을 발견)
        if picture[x][y] == picture[nx][ny]:
            # 3.1 이미 방문한 경우
            if index[nx][ny] == 1:
                continue
            # 3.2 미방문 좌표인 경우 - 팀 소속 좌표 값 리스트에 좌표 추가
            else:
                team[count][2].append((nx, ny))
                team, index = DFS(nx, ny, team, size, picture, index, count)

    return team, index


def init(count, teams, size, maze):
    index = [[0] * n for _ in range(n)]
    for i in range(size):
        for j in range(size):
            if index[i][j] == 0:
                # 팀번호, 팀구성값, 팀좌표들, 인접 다른팀 좌표들
                teams.append((count, maze[i][j], [(i, j)], []))
                teams, index = DFS(i, j, teams, size, maze, index, count)
                count += 1
            else:
                continue
    return teams


# 조화로움 점수들의 합을 answer 배열에 저장한다
def harmony(answer, teams):
    score = 0
    for i in range(len(teams)):
        for j in range(i+1, len(teams)):
            lengths = 0
            for k in range(len(teams[j][3])):
                if teams[j][3][k] in teams[i][2]:
                    lengths += 1
            a = len(teams[i][2])
            b = len(teams[j][2])
            c = teams[i][1]
            d = teams[j][1]
            e = lengths
            if (a+b) * c * d * e > 0:
                score += ((a+b) * c * d * e)
    answer.append(score)
    return answer


# 미로를 회전
def rotate(picture, size):
    # 복사본 생성
    new_picture = copy.deepcopy(picture)
    # 중간좌표 구하기
    middle = size // 2

    # 십자 모양 반시계 회전
    for i in range(size):
        # ㅣ -> ㅡ
        new_picture[middle][i] = picture[i][middle]
        # ㅡ -> ㅣ
        new_picture[(size - 1) - i][middle] = picture[middle][i]

    for i in range(middle):
        for j in range(middle):
            new_picture[j][middle-1-i] = picture[i][j]

    for i in range(middle):
        for j in range(middle):
            new_picture[j][size-1-i] = picture[i][middle+1+j]

    for i in range(middle):
        for j in range(middle):
            new_picture[middle+1+j][middle-1-i] = picture[middle+1+i][j]

    for i in range(middle):
        for j in range(middle):
            new_picture[middle+1+j][size-1-i] = picture[middle+1+i][middle+1+j]

    return new_picture


for i in range(4):
    teams = []
    count = 0

    if i == 0:
        teams = init(count, teams, n, maze)
        answer = harmony(answer, teams)
    else:
        maze = rotate(maze, n)
        teams = init(count, teams, n, maze)
        answer = harmony(answer, teams)

print(sum(answer))



