num = int(input())
total = []
dic = {}
cla = 'Class '

for i in range(num):
    gap = []
    tmp = list(map(int, input().split()))
    tmp.pop(0)
    tmp.sort(reverse=True)
    for j in range(len(tmp) - 1):
        gap.append(tmp[j] - tmp[j+1])
    total.append(tmp)
    dic[i] = max(gap)

for i in range(num):
    target = str(i+1)
    print(cla + target)
    print("Max {0}, Min {1}, Largest gap {2}".format(max(total[i]), min(total[i]), dic[i]))
