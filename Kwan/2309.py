list1 = []
for i in range(9):
    a = int(input())
    list1.append(a)
answer = sum(list1)
list1.sort()
f = 0
s = 1 + f
while True:
    if(answer - list1[f] - list1[s] == 100):
        for i in range(9):
            if(i != f and i != s):
                print(list1[i])
            else:
                continue
        break
    else:
        if(s == 8):
            f += 1
            s = 1 + f
        else:
            s += 1








