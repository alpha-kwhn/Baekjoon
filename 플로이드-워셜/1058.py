N = int(input())
friend = [[0] * N for _ in range(N)]
result = []
answer = 0

for i in range(N):
    words = str(input())
    result.append(words)

for a in range(N):
    for b in range(N):
        for c in range(N):
            if b != c:
                if result[b][c] == "Y" or (result[b][a] == "Y" and result[a][c] == "Y"):
                    friend[b][c] = 1

for i in friend:
    if sum(i) > answer:
        answer = sum(i)

print(answer)
