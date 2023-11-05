num = int(input())
lis = []

if num == 0:
    print(0)
else:
    for _ in range(num):
        tmp = list(map(int, input().split()))
        lis.append(tmp)

    lis = sorted(lis, key=lambda x: x[1])
    flow = [0] * lis[-1][1]

    for item in lis:
        # flow 인덱스 값이 0
        if flow[item[1]-1] == 0:
            flow[item[1]-1] = item[0]
        # flow 인덱스 값이 0보다 큼
        else:
            # 자신이 더 크거나 같은 경우
            if item[0] >= flow[item[1]-1]:
                tmp = flow[item[1]-1]
                flow[item[1] - 1] = item[0]
                # 기존 값 위치 변경 -> 본래 인덱스 앞에 있는 값들중 가장 작은 값에 대체
                target = flow[:item[1]-1]
                if len(target) > 0:
                    mini = min(target)
                    # 앞 값들 중 최소값 보다 대체값이 더 클땐 -> 교체
                    # 그렇지 않으면 값 유기
                    if mini < tmp:
                        idx = target.index(mini)
                        flow[idx] = tmp
            # 자신이 더 작은 경우
            else:
                target = flow[:item[1]-1]
                if len(target) > 0:
                    mini = min(target)
                    if mini < item[0]:
                        idx = target.index(mini)
                        flow[idx] = item[0]

    print(sum(flow))