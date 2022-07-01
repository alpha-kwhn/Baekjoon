import sys
input = sys.stdin.readline

num = int(input())
result = []

for i in range(num):
    paper, want = map(int, input().split())
    important = list(map(int, input().split()))
    index = []

    for p in range(paper):
        index.append(p)

    count = 1
    while True:
        if important[0] != max(important):
            tmp = important.pop(0)
            important.append(tmp)
            tmp2 = index.pop(0)
            index.append(tmp2)

        elif important[0] == max(important):
            if index[0] == want:
                break
            else:
                important.pop(0)
                index.pop(0)
                count += 1
    result.append(count)

for i in result:
    print(i)



