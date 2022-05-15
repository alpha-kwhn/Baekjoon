import sys
import heapq
num = int(input())
lis = []
last = []

for i in range(num):
    start, end = map(int, sys.stdin.readline().split())
    lis.append([start, end])

lis.sort(key=lambda x:x[1])
count = 1
heapq.heappush(last, lis[0][1])

for i in range(1, num):
    if lis[i][0] >= last[0]:
        tmp = heapq.heappop(last)
    else:
        count += 1
    heapq.heappush(last, lis[i][1])

print(count)
