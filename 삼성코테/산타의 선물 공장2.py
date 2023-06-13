q = int(input())

present = 0
start = 0
destination = 0
answer = []

convey = {}


for i in range(q):
    # 공장설립
    if i == 0:
        factory = list(map(int, input().split()))
        # 선물이 위치한 벨트정보 배열 [1 2 2 2 1 4]
        info = factory[3:]
        present = factory[2]

        for p in range(len(info)):
            if info[p]-1 in convey:
                convey[info[p]-1].append(p+1)
            else:
                convey[info[p]-1] = [p+1]
    else:
        change = list(map(int, input().split()))

        # 물건 모두 옮기기
        if change[0] == 200:
            start = change[1] - 1
            destination = change[2] - 1

            if start not in convey:
                if destination not in convey:
                    answer.append(0)
                else:
                    answer.append(len(convey[destination]))
            else:
                if destination not in convey:
                    convey[destination] = convey[start]
                    convey.pop(start)
                    answer.append(len(convey[destination]))
                else:
                    tmp = convey[start]
                    convey.pop(start)
                    convey[destination] = tmp + convey[destination]
                    answer.append(len(convey[destination]))

        # 앞 물건만 교체하기
        elif change[0] == 300:
            start = change[1] - 1
            destination = change[2] - 1
            # 두 벨트가 모두 비어있다면
            if start not in convey and destination in convey:
                tmp = convey[destination].pop(0)
                convey[start] = [tmp]
                if len(convey[destination]) == 0:
                    convey.pop(destination)
            elif start in convey and destination not in convey:
                tmp = convey[start].pop(0)
                convey[destination] = [tmp]
                if len(convey[start]) == 0:
                    convey.pop(start)
            elif start in convey and destination in convey:
                tmp = convey[start][0]
                convey[start][0] = convey[destination][0]
                convey[destination][0] = tmp

            if destination in convey:
                answer.append(len(convey[destination]))
            else:
                answer.append(0)

        # 물건 나누기
        elif change[0] == 400:
            start = change[1] - 1
            destination = change[2] - 1

            if start in convey:
                if len(convey[start]) > 1:
                    floor = len(convey[start]) // 2
                    target = convey[start][:floor]
                    convey[start] = convey[start][floor:]
                    if destination in convey:
                        convey[destination] = target + convey[destination]
                    else:
                        convey[destination] = target
                    answer.append(len(convey[destination]))

            if destination not in convey:
                answer.append(0)

        # 선물 정보 얻기
        elif change[0] == 500:
            for k in convey:
                if change[1] in convey[k]:
                    # 앞 선물 x, 뒷 선물 x
                    if len(convey[k]) == 1:
                        answer.append(-3)
                    else:
                        # 앞 선물 X, 뒷 선물 O
                        if convey[k].index(change[1]) == 0:
                            answer.append(-1 + (2 * convey[k][1]))
                        # 앞 선물 O, 뒷 선물 X
                        elif convey[k].index(change[1]) == (len(convey[k]) - 1):
                            answer.append(convey[k][-2] + (2 * -1))
                        # 앞 선물 O, 뒷 선물 O
                        else:
                            idx = convey[k].index(change[1])
                            answer.append(convey[k][idx-1] + (2 * convey[k][idx+1]))
        # 벨트 정보 얻기
        elif change[0] == 600:
            if change[1]-1 not in convey:
                answer.append(-3)
            else:
                answer.append(convey[change[1] - 1][0] + (2 * convey[change[1] - 1][-1]) + (3 * len(convey[change[1] - 1])))
for i in answer:
    print(i)
