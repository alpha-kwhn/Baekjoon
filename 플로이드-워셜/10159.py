N = int(input())
M = int(input())
scale = [[1e9] * N for _ in range(N)]

for _ in range(M):
    a, b = map(int, input().split())
    scale[a-1][b-1] = 1

for i in range(N):
    scale[i][i] = 0

for a in range(N):
    for b in range(N):
        for c in range(N):
            scale[b][c] = min(scale[b][c], scale[b][a] + scale[a][c])

for a in range(N):
    for b in range(N):
        if 0 < scale[a][b] < 1e9:
            scale[b][a] = scale[a][b]
        elif scale[a][b] == 1e9:
            scale[a][b] = 0

for i in scale:
    print(i.count(0) - 1)