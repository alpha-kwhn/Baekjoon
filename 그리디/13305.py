num = int(input())
lis = list(map(int, input().split()))
liter = list(map(int, input().split()))

cost = 0
cheapest = liter[0]
meter = lis[0]

for i in range(1, len(lis)):
    if liter[i] < cheapest:
        cost += (cheapest * meter)
        meter = lis[i]
        cheapest = liter[i]
    else:
        meter += lis[i]
    if i == len(lis) - 1:
        cost += (meter * cheapest)
print(cost)
