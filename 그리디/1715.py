#알고리즘 구현은 완벽했으나
#heapq를 사용하지 않으면 시간복잡도로 인해 통과가 불가능했던 문제
#log(N)의 시간복잡도
import sys
import heapq

num = int(input())
lis = []

for i in range(num):
    size = int(sys.stdin.readline())
    heapq.heappush(lis, size)

count = 0
tmp = 0
index = 0

while len(lis) > 1:
    first = heapq.heappop(lis)
    second = heapq.heappop(lis)
    count += first + second
    heapq.heappush(lis, first+second)
print(count)
