x, y, w, s = map(int, input().split()) #필요한 값들을 입력 받음
list1 = []
list1.append(w)
list1.append(s)
list3 = []
list3.append(x)
list3.append(y)

p1 = (x + y) * w
p2 = (min(list3) * s) + ((max(list3) - min(list3)) * w)
p4 = 0
if (x + y) % 2 == 0:
    p4 = max(list3) * s
else:
    p4 = ((max(list3) - 1) * s) + w

list2 = []
list2.append(p1)
list2.append(p2)
list2.append(p4)
print(min(list2))


