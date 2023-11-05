import heapq
import sys

num = int(sys.stdin.readline())
heap = []
heap_minus = []

for _ in range(num):
    tmp = int(sys.stdin.readline())
    if tmp != 0:
        if tmp < 0:
            heapq.heappush(heap_minus, -1 * tmp)
        else:
            heapq.heappush(heap, tmp)
    else:
        if heap_minus:
            if heap:
                if heap[0] >= heap_minus[0]:
                    print(heapq.heappop(heap_minus) * -1)
                else:
                    print(heapq.heappop(heap))
            else:
                print(heapq.heappop(heap_minus) * -1)
        else:
            if heap:
                print(heapq.heappop(heap))
            else:
                print(0)






