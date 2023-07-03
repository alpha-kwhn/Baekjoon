N, M = map(int, input().split())
name_1 = []
name_2 = []

for i in range(N):
    name = str(input())
    name_1.append(name)


for i in range(M):
    name = str(input())
    name_2.append(name)

answer = list(set(name_1).intersection(set(name_2)))
answer.sort()

print(len(answer))
for item in answer:
    print(item)
