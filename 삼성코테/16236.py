# 0 : 빈칸
# 1-6: 칸에 있는 물고기의 크기
# 9: 아기 상어의 위치

# 아기 상어의 최초 크기 = 2
# 1초마다 상하좌우 중 한칸 이동
# 자신보다 큰 물고기가 있는 칸을 지나갈 수 없음
# 자기보다 작은 크기의 물고기만 먹을 수 있음
# 크기 같은 물고기가 있는 곳은 지나갈 수는 있으나 먹을 수 없음

from collections import deque

N = int(input())
maze = []
start = []
time = 0

fish = []
size = 2
eat = 0

for i in range(N):
    row = list(map(int, input().split()))

    for j in range(len(row)):
        if row[j] == 9:
            start.append([i, j])
        elif 0 < row[j] <= 6:
            fish.append(row[j])
    maze.append(row)

if len(start) == 0:
    print(0)

# 먹을 수 있는 물고기가 미존재
if len(fish) == 0:
    print(0)

# 물고기가 최소 1개이상 존재하는 경우
else:
    fish.sort()

    # 물고기는 있지만 먹을 수 없는 것들만 있을 때
    if fish[0] >= size:
        print(0)

    # 먹을 수 있는 물고기가 있을 경우
    else:
        def isOK(a, b, width, height):
            return 0 <= a < height and 0 <= b < width


        # 먹을 물고기가 없다면 어미에게 헬프콜
        # 1마리 먹을 수 있으면 그 물고기 먹으러 이동
        # 1마리보다 먹을 수 있는 물고기 수가 많다면 가장 가까운 물고기를 먹으러 간다
        # 위쪽 -> 왼쪽 순서로 우선순위 발동
        # 자기 자신의 크기와 같은 수의 물고기 먹을 때마다 크기가 1 증가

        # 상-좌-우-하
        dir_x = [-1, 0, 0, 1]
        dir_y = [0, -1, 1, 0]
        index = [[0] * N for _ in range(N)]

        # [[[]]]
        queue = deque([start])
        index[start[0][0]][start[0][1]] = 1
        maze[start[0][0]][start[0][1]] = 0
        count = 0

        while queue:
            # [[]]
            target = queue.popleft()
            # []
            box = []
            # 먹이섭취여부
            found = 0
            food = []

            for i in target:
                # 위로이동
                for p in range(4):
                    # 타당한 좌표, 미방문여부 확인
                    if isOK(i[0]+dir_x[p], i[1]+dir_y[p], N, N) and index[i[0] + dir_x[p]][i[1] + dir_y[p]] == 0:
                        # 좌표 값이 0 또는 자신의 크기와 같을 경우 (통행만 가능한 경우)
                        if maze[i[0] + dir_x[p]][i[1] + dir_y[p]] == 0 or maze[i[0] + dir_x[p]][i[1] + dir_y[p]] == size:
                            # 일단 좌표값을 임시저장 + 방문여부 표시
                            box.append([i[0] + dir_x[p], i[1] + dir_y[p]])
                            index[i[0] + dir_x[p]][i[1] + dir_y[p]] = 1
                        # 자신보다 크기가 작은 물고기가 있는 칸인 경우
                        # 해당좌표 값만 queue에 남기고, 식사 횟수 증가 및 크기 확인
                        elif 0 < maze[i[0] + dir_x[p]][i[1] + dir_y[p]] < size:
                            food.append([i[0] + dir_x[p], i[1] + dir_y[p]])
                            index[i[0] + dir_x[p]][i[1] + dir_y[p]] = 1
                            # 먹이찾았음을 표시
                            found = 1

            # 먹이를 발견했을 경우
            if found == 1:
                # 먹이 발견한 곳이 여러곳이라면
                if len(food) > 1:
                    # 높이기준으로 오름차순 정렬
                    food.sort(key=lambda x: x[0])

                eat += 1
                fish.remove(maze[food[0][0]][food[0][1]])
                maze[food[0][0]][food[0][1]] = 0
                food = food[:1]
                count += 1
                time += count
                count = 0

                # 자신의 사이즈만큼 물고기를 먹은 상태라면 크기 증가
                if eat == size:
                    size += 1
                    eat = 0

                # 물고기 다먹었다면
                if len(fish) == 0:
                    break

                # 먹을 수 있는 물고기가 없다면
                elif min(fish) >= size:
                    break

                # 먹을 수 있는 물고기가 존재한다면
                else:
                    queue = deque([food])
                    index = [[0] * N for _ in range(N)]
                    index[food[0][0]][food[0][1]] = 1

            # 먹이를 발견하지 못한 경우
            elif found == 0:
                # 이동이 가능한 경우
                if len(box) > 0:
                    box.sort(key=lambda x: x[0])
                    queue.append(box)
                    count += 1

        print(time)