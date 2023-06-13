# 격자크기, 플레이어 수, 라운드 수
n, m, k = map(int, input().split())
gun = []
players = []
location = {}
# 플레이어번호: [x좌표, y좌표, 공격력, 고유능력, 방향]
# guns: 총 정보만 표시
points = [0] * m

# 총의 정보 입력 - [[1], [2], [3], [0]]
for i in range(n):
    row = list(map(int, input().split()))
    tmp = []
    # 0: 빈칸 그외는 공격력
    for j in row:
        tmp.append([j])
    gun.append(tmp)

# 플레이어 정보 입력 - 위치/방향/초기능력 (상우하좌)
for i in range(m):
    row = list(map(int, input().split()))
    players.append((row[0]-1, row[1]-1))
    location[i] = [(row[0]-1, row[1]-1), 0, row[3], row[2]]


# 좌표 타당성
def isOK(a, b, height, width):
    return 0 <= a < height and 0 <= b < width


# 플레이어끼리 만났을 때 수행하는 총싸움
def battle(num1, num2, location):
    me = location[num1][1] + location[num1][2]
    you = location[num2][1] + location[num2][2]
    score = abs(me - you)

    if me > you:
        return 0, score
    elif me < you:
        return -1, score
    else:
        if location[num1][2] < location[num2][2]:
            return -1, score
        elif location[num1][2] > location[num2][2]:
            return 0, score


# 게임 시작 (x, y), 총, 고유능력, 방향
def game(guns, num, location, players, points):
    dir_x = [-1, 0, 1, 0]
    dir_y = [0, 1, 0, -1]

    nx = location[num][0][0] + dir_x[location[num][3]]
    ny = location[num][0][1] + dir_y[location[num][3]]

    # 좌표 타당성 체크 -> 타당하지 않다면 180도 회전
    if not isOK(nx, ny, n, n):
        # 180도 회전
        location[num][3] = (location[num][3] + 2) % 4
        # 예상좌표값 재조정
        nx = location[num][0][0] + dir_x[location[num][3]]
        ny = location[num][0][1] + dir_y[location[num][3]]

    # 플레이어의 원래 좌표 값 삭제
    players[num] = (-1, -1)

    # 다른 플레이어가 존재하는 경우
    if (nx, ny) in players:
        # 총싸움 진행
        result, score = battle(num, players.index((nx, ny)), location)
        # 내가 승리
        if result == 0:
            points[num] = score
            guns, location, players = afterFight(players.index((nx, ny)), num, location, guns, nx, ny, players)
        # 상대 승리
        else:
            points[players.index((nx, ny))] = score
            guns, location, players = afterFight(num, players.index((nx, ny)), location, guns, nx, ny, players)
    # 플레이어 미존재
    else:
        # 총 존재
        if min(guns[nx][ny]) > 0:
            # 플레이어가 총 가짐
            if location[num][1] > 0:
                # 싸움 위치에 총이 하나 있다면
                if len(guns[nx][ny]) == 1:
                    # 싸움 위치의 총이 더 강력하다면 -> 교환
                    if guns[nx][ny][0] > location[num][1]:
                        tmp = location[num][1]
                        location[num][1] = guns[nx][ny][0]
                        guns[nx][ny][0] = tmp
                else:
                    # 싸움 위치의 총이 더 강력하다면 -> 교환
                    if max(guns[nx][ny]) > location[num][1]:
                        tmp = location[num][1]
                        location[num][1] = max(guns[nx][ny])
                        guns[nx][ny].remove(max(guns[nx][ny]))
                        guns[nx][ny].append(tmp)
            # 플레이어가 총 없을 때
            else:
                # 싸움 위치에 총이 있다면
                if min(guns[nx][ny]) > 0:
                    # 싸움 위치에 총이 하나 있다면
                    if len(guns[nx][ny]) == 1:
                        location[num][1] = guns[nx][ny][0]
                        guns[nx][ny] = [0]
                    # 싸움 위치에 총이 여러개 있다면
                    else:
                        location[num][1] = max(guns[nx][ny])
                        guns[nx][ny].remove(max(guns[nx][ny]))

        # 플레이어 위치 조정
        players[num] = (nx, ny)
        location[num][0] = (nx, ny)
    # 총정보, 사용자 정보 딕셔너리, 사용자 좌표 값, 점수 리턴
    return guns, location, players, points


# 패배행동 (자신, 상대)
def afterFight(lose, win, location, guns, x, y, players):
    dir_x = [-1, 0, 1, 0]
    dir_y = [0, 1, 0, -1]

    # 패배자가 총이 있다면
    if location[lose][1] > 0:
        # 싸움 위치에 총이 있다면
        if min(guns[x][y]) > 0:
            # 총 꾸러미 생성 (기존에 있던 것, 패배자가 버린 것)
            guns[x][y].append(location[lose][1])
            location[lose][1] = 0
        # 싸움 위치에 총이 없다면
        elif min(guns[x][y]) == 0:
            # 총 버리기
            guns[x][y] = [location[lose][1]]
            location[lose][1] = 0

    # 방향이동
    for i in range(4):
        dx = x + dir_x[(location[lose][3] + i) % 4]
        dy = y + dir_y[(location[lose][3] + i) % 4]
        # 목적좌표가 타당한 좌표인지 확인
        if isOK(dx, dy, n, n):
            # 목적좌표에 다른 플레이어가 존재할 경우
            if (dx, dy) in players:
                continue
            # 목적좌표에 다른 플레이어가 존재하지 않을 경우
            else:
                # 목적좌표에 총이 존재하는 경우
                if min(guns[dx][dy]) > 0:
                    # 총이 하나만 있는 경우
                    if len(guns[dx][dy]) == 1:
                        location[lose][1] = guns[dx][dy][0]
                        guns[dx][dy] = [0]
                    # 총이 여러개 있는 경우
                    else:
                        # 목적좌표에 놓여진 총들 중 가장 강력한 총 획득
                        location[lose][1] = max(guns[dx][dy])
                        guns[dx][dy].remove(location[lose][1])
                # 패배자의 변경된 좌표 값 새롭게 등록
                players[lose] = (dx, dy)
                # 패배자 정보 딕셔너리 수정
                location[lose][0] = players[lose]
                # 방향 정보 저장
                location[lose][3] = (location[lose][3] + i) % 4
                # 탐색 즉시종료
                break
        # 타당좌표가 아닌경우
        else:
            continue

    # 승리자가 총을 들고 있는 경우
    if location[win][1] > 0:
        # 싸움 위치에 총이 있다면
        if min(guns[x][y]) > 0:
            # 싸움 위치에 총이 하나만 있다면
            if len(guns[x][y]) == 1:
                # 싸움 위치의 총이 더 강력하다면 -> 교환
                if guns[x][y][0] > location[win][1]:
                    tmp = location[win][1]
                    location[win][1] = guns[x][y][0]
                    guns[x][y][0] = tmp
            # 싸움 위치에 총이 여러개 있다면
            else:
                # 싸움 위치의 총이 더 강력하다면 -> 교환
                if max(guns[x][y]) > location[win][1]:
                    tmp = location[win][1]
                    location[win][1] = max(guns[x][y])
                    guns[x][y].remove(max(guns[x][y]))
                    guns[x][y].append(tmp)
        # 승리자의 변경된 좌표 값 새롭게 등록
        players[win] = (x, y)
        # 승리자 정보 딕셔너리 수정
        location[win][0] = (x, y)

    # 승리자가 총을 안들고 있는 경우
    else:
        # 싸움 위치에 총이 있다면
        if min(guns[x][y]) > 0:
            # 싸움 위치에 총이 하나 있다면
            if len(guns[x][y]) == 1:
                location[win][1] = guns[x][y][0]
                guns[x][y] = [0]
            # 싸움 위치에 총이 여러개 있다면
            else:
                location[win][1] = max(guns[x][y])
                guns[x][y].remove(max(guns[x][y]))

        # 승리자의 변경된 좌표 값 새롭게 등록
        players[win] = (x, y)
        # 승리자 정보 딕셔너리 수정
        location[lose][0] = (x, y)

    return guns, location, players


for i in range(k):
    for j in range(m):
        # 게임실행
        result = game(gun, j, location, players, points)
        gun = result[0]
        location = result[1]
        players = result[2]
        points = result[3]
    print(*points)
print(*points)