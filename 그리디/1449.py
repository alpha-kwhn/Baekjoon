import sys
input = sys.stdin.readline

num, lengths = map(int, input().split())
lis = list(map(int, input().split()))

lis.sort()
target = lis[0]
count = 1

for i in range(1, num):
    if target + (lengths - 1) < lis[i]:
        count += 1
        target = lis[i]

print(count)


