house, modem = map(int, input().split())
location = [int(input()) for _ in range(house)]
location.sort()

start = 1
end = location[-1] - location[0]
answer = 0

if modem == 2:
    print(end)
else:
    while start < end:
        mid = (start + end) // 2
        val = location[0]
        count = 1

        for i in range(1, house):
            if location[i] - val >= mid:
                count += 1
                val = location[i]
        if count >= modem:
            answer = mid
            start = mid + 1
        elif count < modem:
            end = mid
    print(answer)
