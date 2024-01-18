N = int(input())
k = int(input())
arr = []

start = 1
end = k
answer = 0

# 특정 값보다 작거나 같은 숫자의 개수는 min(N, 특정값 // 수)라는 성질값 이용
while True:
    if start > end:
        break
    mid = (start + end) // 2
    count = 0
    for j in range(1, N+1):
        count += min(N, mid // j)
    if count >= k:
        answer = mid
        end = mid - 1
    else:
        start = mid + 1

print(answer)


