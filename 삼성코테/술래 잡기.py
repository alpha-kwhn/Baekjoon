import copy

n, m, h, k = map(int ,input().split())

runner = []
maze = [[0] * n for _ in range(n)]
# 1 = 우, -1 = 좌, 2 = 하, -2 = 상
tree = []

# 항상 오른쪽 아니면 아래쪽을 보고 시작한다
for i in range(m):
    row = list(map(int, input().split()))
    # x, y, 이동방향, 술래와의 거리, 나무존재여부
    distance = abs((row[0]-1) - (n // 2)) + abs((row[1]-1) - (n // 2))
    # 지도에 도망자 표시
    maze[row[0]-1][row[1]-1] = 1
    runner.append([(row[0]-1, row[1]-1), row[2], distance, 0])

# 나무의 좌표
for i in range(h):
    x, y = map(int, input().split())
    tree.append((x-1, y-1))


# 좌표의 타당성 판단
def isOK(a, b, height, width):
    return 0 <= a < height and 0 <= b < width


# 도망자 이동
def run(maze, runner, tree, size):
    new_maze = copy.deepcopy(maze)

    for i in runner:
        # 우/좌 케이스
        if abs(i[1]) == 1:
            # 좌표타당
            nx = i[0][0]
            ny = i[0][1] + i[1]
            if isOK(nx, ny, size, size):
                # 술래가 있을 경우 -> 정지
                if maze[nx][ny] == 2:
                    continue
                # 술래가 없는 경우 -> 이동
                else:
                    # 지도상 위치 변경
                    new_maze[nx][ny] = 1
                    new_maze[i[0][0]][i[0][1]] = 0
                    # 도망자 좌표 업데이트
                    i[0] = (nx, ny)
                    # 나무 존재여부 표시
                    if (nx, ny) in tree:
                        i[3] = 1
            else:
                # 방향전환 (y)
                i[1] *= -1
                ny = i[0][1] + i[1]
                # 술래있는경우
                if maze[nx][ny] == 2:
                    continue
                # 술래없는경우 -> 이동
                else:
                    # 지도상 위치 변경
                    new_maze[nx][ny] = 1
                    new_maze[i[0][0]][i[0][1]] = 0
                    # 도망자 좌표 업데이트
                    i[0] = (nx, ny)
                    # 나무 존재여부 표시
                    if (nx, ny) in tree:
                        i[3] = 1
        # 하/상
        elif abs(i[1]) == 2:
            dir_x = 0
            if i[1] < 0:
                dir_x = -1
            else:
                dir_x = 1
            # 좌표타당
            nx = i[0][0] + dir_x
            ny = i[0][1]
            if isOK(nx, ny, size, size):
                # 술래가 있을 경우 -> 정지
                if maze[nx][ny] == 2:
                    continue
                # 술래가 없는 경우 -> 이동
                else:
                    # 지도상 위치 변경
                    new_maze[nx][ny] = 1
                    new_maze[i[0][0]][i[0][1]] = 0
                    # 도망자 좌표 업데이트
                    i[0] = (nx, ny)
            else:
                # 방향전환 (y)
                dir_x *= -1
                i[1] *= -1
                nx = i[0][1] + dir_x
                # 술래있는경우
                if maze[nx][ny] == 2:
                    continue
                # 술래없는경우 -> 이동
                else:
                    # 지도상 위치 변경
                    new_maze[nx][ny] = 1
                    new_maze[i[0][0]][i[0][1]] = 0
                    # 도망자 좌표 업데이트
                    i[0] = (nx, ny)

    return new_maze, runner


# 지도세팅
for i in range(k):
    # 지도에 술래 표시
    king = (n // 2, n // 2)
    maze[king[0]][king[1]] = 2
    # 술래의 다음 위치 예상



    # 도망 실행
    maze, runner = run(maze, runner, tree, n)
    print(runner)
