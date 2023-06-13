from collections import deque
import copy

number = int(input())
maze = []
special_maze = []
index = [[0] * number for _ in range(number)]
special_index = [[0] * number for _ in range(number)]


# 지도상 좌표가 타당하며 거쳤던 곳이 아닌 좌표인지 확인
def isOk(x, y, lens):
    return 0 <= x < lens and 0 <= y < lens


# 지도 만들어주기
def init(width):
    tmp = [list(map(str, input())) for _ in range(width)]
    return tmp


def special_init():
    disable = copy.deepcopy(maze)
    for i in disable:
        for j in range(len(i)):
            if i[j] == "G":
                i[j] = "R"
    return disable


# 지도 속 구역의 수 구하기
queue = deque([[(0, 0)]])
special_queue = deque([[(0, 0)]])
diff = []
special_diff = []

# 방향 좌표
dir_x = [1, 0, -1, 0]
dir_y = [0, 1, 0, -1]


# 일반인 기준 구역 탐색
def search(count, height):
    while queue:
        target = queue.popleft()
        container = []

        for idx in target:
            color = maze[idx[0]][idx[1]]
            index[idx[0]][idx[1]] = 1

            for case in range(4):
                dx = idx[0] + dir_x[case]
                dy = idx[1] + dir_y[case]

                # 타당 좌표 값이고, 색상이 탐색 시작점과 일치
                if isOk(dx, dy, height):
                    if maze[dx][dy] == color and index[dx][dy] == 0:
                        index[dx][dy] = 1
                        container.append((dx, dy))
                    elif maze[dx][dy] != color and index[dx][dy] == 0:
                        diff.append((dx, dy))

        # container 안 비어있다면 -> 영역 탐색 가능
        if len(container) != 0:
            queue.append(container)
        else:
            count += 1
            while len(diff) > 0:
                if index[diff[0][0]][diff[0][1]] == 0:
                    item = diff.pop(0)
                    queue.append([item])
                    break
                else:
                    diff.pop(0)

            if len(queue) == 0:
                return count


def special_search(count, height):
    while special_queue:
        target = special_queue.popleft()
        container = []

        for idx in target:
            color = special_maze[idx[0]][idx[1]]
            special_index[idx[0]][idx[1]] = 1

            for case in range(4):
                dx = idx[0] + dir_x[case]
                dy = idx[1] + dir_y[case]

                # 타당 좌표 값이고, 색상이 탐색 시작점과 일치
                if isOk(dx, dy, height):
                    if special_maze[dx][dy] == color and special_index[dx][dy] == 0:
                        special_index[dx][dy] = 1
                        container.append((dx, dy))
                    elif special_maze[dx][dy] != color and special_index[dx][dy] == 0:
                        special_diff.append((dx, dy))

        # container 안 비어있다면 -> 영역 탐색 가능
        if len(container) != 0:
            special_queue.append(container)
        else:
            count += 1
            while len(special_diff) > 0:
                if special_index[special_diff[0][0]][special_diff[0][1]] == 0:
                    item = special_diff.pop(0)
                    special_queue.append([item])
                    break
                else:
                    special_diff.pop(0)

            if len(special_queue) == 0:
                return count


maze = init(number)
special_maze = special_init()
normal = search(0, number)
special = special_search(0, number)

print(normal, special)
