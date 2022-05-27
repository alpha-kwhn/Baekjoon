#시간복잡도 측면에서는 list 보다 set가 유리하다
# pypy3 는 python3 보다 메모리 측면에서 불리하다
import sys
input = sys.stdin.readline

num = int(input())
lis = set()

for i in range(num):
    option = input().strip().split()

    if len(option) == 1:
        if option[0] == "all":
            lis = set([i for i in range(1, 21)])

        elif option[0] == "empty":
            lis = set()

    else:
        operate, x = option[0], int(option[1])

        if operate == "add":
            lis.add(x)

        elif operate == "check":
            if x in lis:
                print(1)
            else:
                print(0)

        elif operate == "remove":
            lis.discard(x)

        elif operate == "toggle":
            if x in lis:
                lis.discard(x)
            else:
                lis.add(x)

