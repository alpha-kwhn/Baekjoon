import sys
input = sys.stdin.readline

size = int(input())
ocean = [[0]*size]*size
start = [0, 0]

for i in range(size):
    tmp = list(map(int, input().split()))
    ocean[i] = tmp
    if 9 in tmp:
        j = tmp.index(9)
        start = [i, j]


def BFS(maze, target, x, y, count, shark_size, answer):
    if 0 <= x < size and 0 <= y < size:
        if maze[x][y] in target:
            answer += 1
            if maze[x][y] < target[-1]:
                count += 1
                if count == shark_size:
                    shark_size += 1
                    maze[x][y] = 0
                    target.append(target[-1] + 1)
                    count = 0
            BFS(maze, target, x+1, y, count, shark_size, answer)
            BFS(maze, target, x-1, y, count, shark_size, answer)
            BFS(maze, target, x, y+1, count, shark_size, answer)
            BFS(maze, target, x, y-1, count, shark_size, answer)

        elif maze[x][y] not in target:
            print(answer)


BFS(ocean, [0, 1, 2], start[0], start[1], 0, 2, 0)



















