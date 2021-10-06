n, l, d = list(map(int, input().split()))
mount = (l * n) + ((n - 1)*5)
flag = 0
for i in range(1, int(mount // d) + 1):
    if (d * i) % (l + 5) in range(0, l):
        continue
    else:
        print(d * i)
        flag = 1
        break
if flag == 0:
    count = 1
    while (n * l) + (5 * (n - 1)) >= count * d:
        count += 1
    print(count * d)

