num = int(input())
list2 = []

for i in range(num):
    a, b = map(int, input().split())
    a = a % 10

    if a == 1 or a == 5 or a == 6:
        list2.append(a)
    elif a == 2 or a == 3 or a == 7 or a == 8:
        if(b % 4 == 0):
            list2.append((a**4) % 10)
        else:
            list2.append((a**(b % 4)) % 10)
    elif a == 4 or a == 9:
        if(b % 2 == 1):
            list2.append(a)
        else:
            list2.append((a*a) % 10)
    elif a == 0:
        list2.append(10)

for i in range(len(list2)):
    print(list2[i], end = '\n')


