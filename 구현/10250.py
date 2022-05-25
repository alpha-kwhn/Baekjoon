import sys
input = sys.stdin.readline

testcase = int(input())
lis = []

for i in range(testcase):
    height, room, rank = map(int, input().split())
    check = rank % height
    answer = ""

    if check == 0:
        if len(str(rank // height)) == 1:
            answer = str(height) + '0' + str(rank//height)
        else:
            answer = str(height) + str(rank//height)

    else:
        if len(str((rank // height) + 1)) == 1:
            answer = str(check) + '0' + str((rank//height)+1)
        else:
            answer = str(check) + str((rank//height)+1)
    lis.append(int(answer))

for i in lis:
    print(i)

