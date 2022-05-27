import sys
from collections import Counter
input = sys.stdin.readline

num = int(input())
lis = []

# 숫자 입력
for i in range(num):
    tmp = int(input())
    lis.append(tmp)

# 1. 산술 평균
print(round(sum(lis) / num))

# 2. 중앙값
lis.sort()
if len(lis) % 2 == 1:
    print(lis[len(lis) // 2])
else:
    tmp = round((lis[(len(lis) // 2)-1] + lis[len(lis) // 2]) / 2)
    print(tmp)

# 3. 최빈값
if len(lis) == 1:
    print(lis[0])

else:
    cnt = Counter(lis)
    answer = cnt.most_common(2)

    if answer[0][1] == answer[1][1]:
        print(answer[1][0])
    else:
        if answer[0][1] > answer[1][1]:
            print(answer[0][0])
        else:
            print(answer[1][0])

# 4. 범위
if len(lis) == 1:
    print(0)
else:
    print(lis[-1] - lis[0])








