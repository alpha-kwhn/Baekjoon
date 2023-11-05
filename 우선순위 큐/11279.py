import heapq
import sys

num = int(sys.stdin.readline())
heap = []
answer = []

for _ in range(num):
    tmp = int(sys.stdin.readline())
    if tmp == 0:
        if heap:
            print(heapq.heappop(heap) * -1)
        else:
            print(0)
    else:
        heapq.heappush(heap, (tmp * -1))