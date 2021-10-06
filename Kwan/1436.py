num = int(input())
start = 666
answer = 0
while True:
    if '666' in str(start):
        answer += 1
        if(answer == num):
            print(start)
            break
    start += 1



