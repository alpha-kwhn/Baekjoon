list1 = [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1]
list2 = []
judge = ''

while True:
    num = int(input())
    if num != 0:
        judge = str(input())
        if judge == 'too high':
            for i in range(num, 11):
                list1[i] = 0
        elif judge == 'too low':
            for i in range(0, num+1):
                list1[i] = 0
        elif judge == 'right on':
            if list1[num] == 1:
                list2.append('Stan may be honest')
            else:
                list2.append('Stan is dishonest')
            list1 = [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1] #초기화
    elif num == 0:
        for i in range(len(list2)):
            print(list2[i], end = '\n')
        break
