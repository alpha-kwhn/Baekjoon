num, money = map(int, input().split())
lis = []
count = 0

for i in range(num):
    tmp = int(input())
    lis.append(tmp)
#입력 조건 맞춤 세팅

#사용가능한 동전 단위를 색출해내기 위한 flag 변수 및 알고리즘
flag = 0
while(True):
    if lis[flag] > money: #money보다 동전 단위가 클 때 바로 break
        break
    elif lis[-1] <= money: #제일 큰 동전 단위가 money보다 작거나 같을 경우
        flag = len(lis)
        break
    else: #money보다 동전 단위가 작은 경우엔 계속 flag 값을 늘려나가야 한다
        flag += 1

while(True): #1원으로만 계산해야되는 상황 왔을경우엔 남은 잔액을 더하면 됨
    if flag == 1:
        count += money
        break
    else: #동전 단위 별 사용 개수 누적 count
        count += money // lis[flag-1]
        money = money % lis[flag-1]
        flag -= 1

print(count)



