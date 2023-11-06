import sys

def check(target):
    for i in range(len(target)):
        for j in range(i+1, len(target)):
            if target[i] == target[j][:len(target[i])]:
                return "NO"
    return "YES"

num = int(sys.stdin.readline())

for _ in range(num):
    phone = int(input())
    lis = []
    for _ in range(phone):
        tmp = sys.stdin.readline().rstrip()
        lis.append(tmp)
    lis.sort()
    print(check(lis))