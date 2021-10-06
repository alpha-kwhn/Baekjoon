a, b = map(int, input().split())
c = int(input())
list1 = list(map(int, input().split()))

list2 = []
answer = 0

for j in range(0, c):
    answer = answer + int(list1[j] * (a**(int(c - 1 - j))))

ptr = int(answer)
while ptr != 0:
    list2.append(str(ptr % b))
    ptr = ptr // b

print(' '.join((list2[::-1])))

