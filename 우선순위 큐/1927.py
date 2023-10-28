import heapq

num = int(input())
heap_q = []
answer = []

for i in range(num):
    tmp = int(input())
    if tmp == 0:
        if len(heap_q) == 0:
            answer.append(0)
        else:
            answer.append(heapq.heappop(heap_q))
    else:
        heapq.heappush(heap_q, tmp)

for i in answer:
    print(i)
