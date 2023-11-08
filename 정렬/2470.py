import sys

num = int(sys.stdin.readline().rstrip())
lis = list(map(int, sys.stdin.readline().rstrip().split()))
lis = sorted(lis, key=lambda x:abs(x))

mini = (abs(lis[0] + lis[1]), lis[0], lis[1])

if num == 2:
    print(min(lis[0], lis[1]), max(lis[0], lis[1]))
else:
    for i in range(1, len(lis) - 1):
        tmp = abs(lis[i] + lis[i+1])
        if tmp <= mini[0]:
            mini = (tmp, lis[i], lis[i+1])
    print(min(mini[1], mini[2]), max(mini[1], mini[2]))


