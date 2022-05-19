import sys
input = sys.stdin.readline

hole, use = map(int, input().split())
lis = list(map(int, input().split()))
target = []
count = 0
flag = 0

for i in range(0, len(lis)):
    if lis[i] in target:
        pass
    elif len(target) < hole:
        target.append(lis[i])
    else:
        for k in target:
            if k not in lis[i:]:
                tmp = k
                break
            #이 부분의 구현이 핵심
            #제일 나중에 재등장하는 요소를 교체해야함
            elif lis[i:].index(k) > flag:
                flag = lis[i:].index(k)
                tmp = k
        target[target.index(tmp)] = lis[i]
        flag = 0
        tmp = 0
        count += 1
print(count)


