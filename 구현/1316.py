import sys
input = sys.stdin.readline

num = int(input())
lis = []

for i in range(num):
    tmp = input()
    lis.append(tmp)


count = 0
for i in lis:
    tmp = []
    flag = 1
    if len(i) > 1:
        for j in range(len(i)):
            if i[j] not in tmp:
                tmp.append(i[j])
            else:
                if i[j] != tmp[-1]:
                    flag = 0
                    break
    if flag == 1:
        count += 1
print(count)
