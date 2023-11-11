import sys

num = int(sys.stdin.readline().rstrip())
tower = list(map(int, input().split()))
result = []
top = 0
answer = []

for idx in range(len(tower)):
    if len(result) == 0:
        result.append((tower[idx], idx))
        answer.append(0)
    else:
        if result[-1][0] <= tower[idx]:
            while len(result) > 1 and result[-1][0] <= tower[idx]:
                result.pop()
            if len(result) == 1:
                if tower[idx] < result[0][0]:
                    answer.append(result[0][1] + 1)
                else:
                    answer.append(0)
                result.append((tower[idx], idx))
            else:
                answer.append(result[-1][1] + 1)
                result.append((tower[idx], idx))
        else:
            answer.append(result[-1][1] + 1)
            result.append((tower[idx], idx))

for item in answer:
    print(item, end=" ")









