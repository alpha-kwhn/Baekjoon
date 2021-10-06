num = int(input())
list1 = []
list2 = []
for i in range(num):
    h, w = map(int, input().split())
    list1.append([h, w]) #키 몸무게 리스트 쌍 추가

for k in range(num):
    list2.append(1)

for i in range(num):
    for k in range(num):
        if(i != k):
            if((list1[i][0] < list1[k][0]) & (list1[i][1] < list1[k][1])):
                list2[i] += 1


for j in range(num):
    print(int(list2[j]), end = ' ')






