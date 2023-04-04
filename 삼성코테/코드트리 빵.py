from collections import deque

n, m = map(int, input().split())
maze = []
cvs = []
answer = [0] * m

# 0 빈곳, 1 베이스캠프
for i in range(n):
    row = list(map(int, input().split()))
    maze.append(row)

# 가려는 편의점 위치 저장
for i in range(m):
    x, y = map(int, input().split())
    cvs.append([x-1, y-1])


def isOK(a, b, width, height):
    return 0 <= a < height and 0 <= b < width


# 편의점과 가장 가까운 베이스캠프를 찾는 함수
def BFS(a, b, index):
    dir_x = [-1, 0, 0, 1]
    dir_y = [0, -1, 1, 0]
    queue = deque([(a, b)])
    index[a][b] = 0

    while queue:
        a, b = queue.popleft()
        camp = []
        for k in range(4):
            nx = a + dir_x[k]
            ny = b + dir_y[k]
            # 타당한 좌표 + 아직 지나치지 않은 거칠 수 있는 곳
            if isOK(nx, ny, n, n) and index[nx][ny] == -1:
                # 빈 곳
                if maze[nx][ny] == 0:
                    queue.append((nx, ny))
                    # 시간 증가
                    index[nx][ny] = index[a][b] + 1
                # 베이스캠프
                elif maze[nx][ny] == 1:
                    # 발견한 캠프 좌표 값 넣어주기
                    camp.append([nx, ny])
                    # 시간증가
                    index[nx][ny] = index[a][b] + 1
        if len(camp) >= 1:
            camp.sort(key=lambda x:x[0])
            # 베이스캠프까지 가는데 걸리는 시간을 리턴
            x = camp[0][0]
            y = camp[0][1]
            return [index[x][y], x, y]


time = 1
black = []

while True:
    index = [[-1] * n for _ in range(n)]

    # 못 지나가는 구역 영역표시
    for b in black:
        tx, ty = b
        index[tx][ty] = -2

    # 3번 과정
    if time <= m:
        # 베이스캠프 탐색 진행
        ans = BFS(cvs[time-1][0], cvs[time-1][1], index)
        house = ans[0]
        nx = ans[1]
        ny = ans[2]
        black.append((nx, ny))
        # 베이스캠프에서 편의점까지 걸리는 시간 기록
        answer[time-1] = house

        for i in range(0, time):
            if answer[i] == 0:
                continue
            else:
                answer[i] -= 1
                # 편의점 도착
                if answer[i] == 0:
                    # 해당 편의점은 이제 아무도 못 지나감을 표시
                    black.append((cvs[i][0], cvs[i][1]))

    # 편의점 미도착자 확인
    if time > m:
        # 편의점 도착 다 했는지 확인
        if len(answer) == answer.count(0):
            break
        else:
            for i in range(0, m):
                if answer[i] == 0:
                    continue
                else:
                    answer[i] -= 1
                    # 편의점 도착
                    if answer[i] == 0:
                        # 해당 편의점은 이제 아무도 못 지나감을 표시
                        black.append((cvs[i][0], cvs[i][1]))
    time += 1

print(time)

