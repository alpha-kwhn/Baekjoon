N, M = map(int, input().split())
diction = []
answer = 0

for i in range(N):
    tmp = str(input())
    diction.append(tmp)

for i in range(M):
    tmp = str(input())

    if tmp in diction:
        answer += 1

print(answer)
