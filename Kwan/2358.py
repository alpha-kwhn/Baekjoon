num = int(input())
list1 = []
list2 = []
for i in range(num):
    a, b = map(int, input().split())
    list1.append(a)
    list2.append(b)
count = 0
list1.sort()
list2.sort()
list3 = []
list4 = []
for i in range(len(list1)):
    for k in range(i+1, len(list1)):
        if list1[i] == list1[k] and list1[i] not in list3:
            count += 1
            list3.append(list1[i])
            break
        else:
            break
            #x좌표가 같은 것 나와도, 겹치는 건 하나로 취급, y도 마찬가지
for i in range(len(list2)):
    for k in range(1+i, len(list2)):
        if list2[i] == list2[k] and list2[i] not in list4:
            count += 1
            list4.append(list2[i])
            break
        else:
            break
print(count)
