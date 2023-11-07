import sys

num, maxi = map(int, sys.stdin.readline().rstrip().split())
answer = []
count = []

tmp = list(map(int, sys.stdin.readline().rstrip().split()))
test = list(set(tmp))

if len(tmp) == num:
    for i in range(len(test)):
        count.append([tmp.count(test[i]), test[i], tmp.index(test[i])])

    count = sorted(count, key=lambda x:(-x[0], x[2]))

    for i in count:
        print(' '.join(list(map(str, [i[1]] * i[0]))), end=" ")