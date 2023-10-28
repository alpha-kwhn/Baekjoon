num = int(input())
lis = list(map(int, input().split()))
answer = [-1] * num
waiting = [0]

# 오큰수 구하기
for item in range(1, len(lis)):
    while waiting:
        if lis[waiting[-1]] < lis[item]:
            answer[waiting.pop()] = lis[item]
        else:
            break
    waiting.append(item)

for item in answer:
    print(item, end=" ")

