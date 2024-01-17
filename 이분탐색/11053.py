N = int(input())
arr = list(map(int, input().split()))
answer = [arr[0]]
record = [1]
lengths = 1

for i in range(1, N):
    if lengths == 1:
        if arr[i] > answer[-1]:
            answer.append(arr[i])
            lengths += 1
            record.append(lengths)
        if arr[i] < answer[-1]:
            answer[-1] = arr[i]
            record.append(lengths)
    else:
        if arr[i] < answer[-1]:
            left = 0
            right = lengths - 1
            while left < right:
                mid = (left + right) // 2
                if answer[mid] < arr[i]:
                    left = mid + 1
                else:
                    right = mid
            answer[right] = arr[i]
            record.append(lengths)
        elif arr[i] > answer[-1]:
            answer.append(arr[i])
            lengths += 1
            record.append(lengths)

print(len(list(set(record))))