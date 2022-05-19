import sys
input = sys.stdin.readline

num = int(input())
lis = []

for i in range(num):
    lis.append(list(map(int, input().split())))
lis.sort()
res = [0] * 1000
jam = []

for i in range(len(lis)):
    if res[lis[i][0]-1] == 0:
        res[lis[i][0]-1] = lis[i][1]
    else:
        if res[lis[i][0]-1] < lis[i][1]:
            tmp = res[lis[i][0]-1]
            res[lis[i][0] - 1] = lis[i][1]
            jam.append([lis[i][0], tmp])
        else:
            jam.append([lis[i][0], lis[i][1]])

if len(jam) != 0:
    for i in range(len(jam)):
        if min(res[:jam[i][0]]) < jam[i][1]:
            res[res.index(min(res[:jam[i][0]]))] = jam[i][1]

print(sum(res))


