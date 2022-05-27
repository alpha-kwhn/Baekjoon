#조합 말고는 답이없다.. 시간복잡도 1초 선에서 통과 가능한 문제
import sys
from itertools import combinations
input = sys.stdin.readline

size, save = map(int, input().split())
lis = [[0]*size]*size
chicken = []
house = []
answer = []

for i in range(size):
    tmp = list(map(int, input().split()))
    lis[i] = tmp
    for j in range(size):
        if lis[i][j] == 1:
            house.append([i, j])
        elif lis[i][j] == 2:
            chicken.append([i, j])

for market in combinations(chicken, save):
    tmp = 0
    for home in house:
        far = 101
        for i in range(save):
            far = min(far, abs(home[0] - market[i][0]) + abs(home[1] - market[i][1]))
        tmp += far
    answer.append(tmp)

print(min(answer))




