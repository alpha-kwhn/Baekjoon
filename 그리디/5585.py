times = int(input())
lis = [5, 1, 10]
result = [0, 0, 0]

minute = times // 60
seconds = (times % 60) // 10
verify = (times % 60) % 10

if verify != 0:
    print(-1)

else:
    result[2] = seconds
    result[0] = minute // 5
    result[1] = minute % 5

    print(result[0], result[1], result[2])


