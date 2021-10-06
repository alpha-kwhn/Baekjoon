num = int(input())
list1 = []
list2 = []
list3 = []
check = []
distance = 0

for i in range(num):
    for _ in range(4):
        a, b = map(int, input().split())
        list1.append(a)
        list2.append(b) #x, y좌표를 각각 리스트에 저장

    for i in range(0, 4):
        for k in range(i+1, 4):
            distance = ((list1[k] - list1[i])**2) + ((list2[k] - list2[i])**2)
            list3.append(distance)
    list3.sort()
    if list3[0] == list3[1] and list3[1] == list3[2] and list3[2] == list3[3] and list3[3] == list3[0] and list3[4] == list3[5]:
        print(1)
    else:
        print(0)
    list1.clear()
    list2.clear()
    list3.clear()



