num = int(input())
times = list(map(int, input().split()))

times.sort()

#같은 시간 안에 최대한 많은 사람들이 돈을 인출해야한다
total = 0
for i in range(len(times)):
    total += sum(times[:i+1])

print(total)