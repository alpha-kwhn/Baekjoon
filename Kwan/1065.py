num = int(input())
list1 = [111, 123, 135, 147, 159, 210, 222, 234, 246, 258, 321, 333, 345, 357,369,
         420, 432, 444, 456, 468, 531, 543, 555, 567, 579, 630, 642, 654, 666, 678,
         741, 753, 765, 777, 789, 840, 852, 864, 876, 888, 951, 963, 975, 987, 999]
if num <= 99:
    print(num)
elif num > 99 and num < 111:
    print(99)
elif num >= 111 and num <= 998:
    for i in range(len(list1)):
        if num < list1[i]:
            print(99+i)
            break
elif num == 999 or num == 1000:
    print(144)



