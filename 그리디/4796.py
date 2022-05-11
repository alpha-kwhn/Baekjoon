lis = []

while(True):
    able, total, vacation = map(int, input().split())
    if able == 0 and total == 0 and vacation == 0:
        break
    else:
        count = (vacation // total) * able
        if vacation % total <= able:
            count += (vacation % total)
        else:
            count += able
        lis.append(count)

for i in range(len(lis)):
    print("Case " + str(i+1) + ": " + str(lis[i]))






