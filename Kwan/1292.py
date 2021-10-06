a, b = map(int, input().split())
num = 1
count = 0
list1 = []
while count < b:
    for i in range(num):
        list1.append(num)
        count += 1
    num += 1
list2 = list1[a-1:b]
print(sum(list2))
