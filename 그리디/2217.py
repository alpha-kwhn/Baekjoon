import sys
num = int(input())
lis = []
sizes = []

for i in range(num):
    tmp = float(sys.stdin.readline())
    lis.append(tmp)

if len(lis) == 1:
    print(int(lis[0]))

else:
    lis.sort(reverse=True)
    for i in range(0, len(lis)):
        sizes.append(float(i+1) * lis[i])
    result = int(max(sizes))
    print(result)

