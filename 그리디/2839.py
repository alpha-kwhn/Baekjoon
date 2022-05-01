# 3키로와 5키로 봉지가 있다
# 최대한 적은 봉지 수 가져가는 것이 목표


total = int(input())
count = 0

if total % 3 == 0 and total % 5 != 0:
    tmp = total
    if total < 8:
        print(total // 3)
    else:
        #최소한의 3키로봉지 최대의 5키로 봉지를 이용해 담아야 한다.
        while(True):
            total -= 3
            count += 1
            if total % 5 == 0:
                print(count + (total // 5))
                break
            if total < 3:
                print(tmp // 3)
                break

elif total % 5 == 0 and total % 3 != 0:
    print(total // 5)

elif total % 5 == 0 and total % 3 == 0:
    print(total // 5)

else:
    tmp = total
    if total < 8:
        print(-1)
    else:
        while (True):
            total -= 3
            count += 1
            if total % 5 == 0:
                print(count + (total // 5))
                break
            if total < 3:
                print(-1)
                break

















