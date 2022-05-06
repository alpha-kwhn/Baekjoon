num = int(input())
lis = list(map(int, str(num)))

if 0 not in lis:
    print(-1)

else:
    lis.sort(reverse=True)
    if sum(lis) % 3 == 0:
        result = ''.join(map(str, lis))
        print(int(result))
    else:
        print(-1)

