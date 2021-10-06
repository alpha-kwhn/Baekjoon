bee = int(input())
num = 1

while True:
    tmp = (3 * num * num) + (3 * num) + 1
    if bee == 1:
        print(1)
        break
    elif bee <= tmp:
        print(num + 1)
        break
    else:
        num = num + 1