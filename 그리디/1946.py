import sys
testcase = int(input())

for i in range(testcase):
    tmp = []
    lengths = int(input())
    for j in range(lengths):
        tests, face = map(int, sys.stdin.readline().split())
        tmp.append([tests, face])

    tmp.sort()
    target = tmp[0][1]
    count = 1
    for i in range(1, lengths):
        if target > tmp[i][1]:
            count += 1
            target = tmp[i][1]

    print(count)

    #숫자(순위)가 낮을수록 우선순위가 높다는 의미임을 잘 이해해야함(이해못해서 오래걸림)
    #따라서 서류나 면접 점수 순위기준으로 오름차순 정렬을 하면 맨앞사람은 일단 합격임
    #서류기준정렬했을 때 기준으로,,, 서류점수를 이길 수 있는 사람은 0번째 사람 말곤 없다
    #따라서 나머지는 면접이 그 사람보다 높아야한다
    #면접이 더 높은사람을 세기 위한 count를 반복문을 통해 집계함
    #출력하면 문제풀이 완료
    #시간초과가 떠서 sys.stdin.readline()을 사용함
    #반복문으로 여러 줄을 입력받을 때는 위 메소드를 사용해야 시간절약이 가능함








for i in tmp:
    print(i)



