import heapq

num, combine = map(int, input().split())
card = list(map(int, input().split()))
heapq.heapify(card)

for i in range(combine):
    container= []
    a = heapq.heappop(card)
    b = heapq.heappop(card)
    heapq.heappush(card, a+b)
    heapq.heappush(card, a+b)
print(sum(card))
