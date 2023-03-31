n = int(input())
info = []

for i in range(n):
    money = list(map(int, input().split()))
    info.append(money)

money = [0 for i in range(n+1)]

for i in range(n):
    for j in range(i+info[i][0], n+1):
        if money[j] < money[i] + info[i][1]:
            money[j] = money[i] + info[i][1]
print(money[-1])





















