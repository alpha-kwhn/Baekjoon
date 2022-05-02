num = int(input())

lisa = list(map(int, input().split()))
lisb = list(map(int, input().split()))

lisa.sort()
lisb.sort(reverse=True)

answer = [x*y for x,y in zip(lisa, lisb)]
print(sum(answer))