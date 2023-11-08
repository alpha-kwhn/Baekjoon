a, b = list(map(int, input().split()))
first = []
second = []
answer = []

first += list(map(int, input().split()))
second += list(map(int, input().split()))

answer += first
answer += second
answer.sort()

for i in answer:
    print(i, end=" ")




