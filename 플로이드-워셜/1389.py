N, relation = map(int, input().split())
friend = [[1e9] * N for _ in range(N)]
answer = 0

for idx in range(N):
    friend[idx][idx] = 0

for _ in range(relation):
    a, b = map(int, input().split())
    friend[a-1][b-1] = 1
    friend[b-1][a-1] = 1

for a in range(N):
    for b in range(N):
        for c in range(N):
            friend[b][c] = min(friend[b][c], friend[b][a] + friend[a][c])

answer = sum(friend[0])
result = [1]

for i in range(1, N):
    val = sum(friend[i])
    if val < answer:
        answer = val
        result.clear()
        result.append(i+1)
    elif val == answer:
        result.append(i+1)

result.sort()
print(result[0])

