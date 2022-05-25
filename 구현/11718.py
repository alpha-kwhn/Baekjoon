import sys
input = sys.stdin.readline
lis = []

for i in range(100):
    tmp = str(input().rstrip())
    if len(tmp) == 0:
        break
    else:
        lis.append(tmp)

for i in lis:
    print(i)

