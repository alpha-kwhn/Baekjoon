N = int(input())
words = []

for i in range(N):
    word = str(input())
    words.append(word)

# set화를 통해 중복 제거 -> sort()로 사전순 정렬 -> sort(key=len)을 통해 길이순 오름차순 정렬
answer = list(set(words))
answer.sort()
answer.sort(key=len)

for item in answer:
    print(item)
