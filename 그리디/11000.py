##시간복잡도를 최대한 줄여보자##


import sys
import heapq
input = sys.stdin.readline

num = int(input())
lis = []
last = []

for i in range(num):
    start, end = map(int, input().split())
    lis.append([start, end])

lis.sort(key=lambda x:x[0])
heapq.heappush(last, lis[0][1])

for i in range(1, num):
    if lis[i][0] >= last[0]:
        heapq.heappop(last)
    heapq.heappush(last, lis[i][1])

print(len(last))
