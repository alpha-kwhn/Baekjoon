num = int(input())
lis = []

for i in range(num):
    start, end = map(int, input().split())
    lis.append([start, end])

lis.sort(key=lambda x: (x[1], x[0]))
#두 번째 값 기준으로 오름차순 정렬

count = 1
ending = lis[0][1]

for move in range(1, num):
    if lis[move][0] >= ending:
        ending = lis[move][1]
        count += 1

print(count)
