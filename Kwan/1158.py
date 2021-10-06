n, k = map(int, input().split())
list1 = []
list2 = []
for i in range(1, n+1):
    list1.append(str(i)) #list에 값 집어넣기

index = k - 1
list2.append('<'+list1[index])
list1[index] = 0
index = (index + 1) % len(list1)

count = 0
sta = 1

while True:
    if(list1[index] != 0 & count != k):
        count += 1
        if(count == k):
            count = 0
            list2.append(list1[index])
            list1[index] = 0
            sta += 1
            if(sta == n):
                break
    index = (index + 1) % len(list1)

for i in range(n - 1):
    print(list2[i], end =',')
print(list2[-1]+'>')





