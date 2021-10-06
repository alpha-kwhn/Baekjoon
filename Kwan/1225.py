a, b = map(str, input().split())
list1 = list(a)
list2 = list(b)
answer1 = 0
answer2 = 0
for i in range(len(list1)):
    answer1 += int(list1[i])
for j in range(len(list2)):
    answer2 += int(list2[j])

print(answer1 * answer2)