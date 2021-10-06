n, k = map(int, input().split())
list1 = []
list2 = []
for i in range(1, n+1):
    list1.append(str(i)) #list에 값 집어넣기
index = 0

while len(list1) > 0:
    index = (index + k - 1) % len(list1)
    list2.append(list1[index])
    del list1[index]

print("<%s>" %(", ".join(list2)))








