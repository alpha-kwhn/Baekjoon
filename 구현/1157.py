import sys
input = sys.stdin.readline

word = input().rstrip()
word = word.lower()
compose = list(set(list(word)))

num = []
biggest = compose[0]
num.append(biggest)
index = 0

if len(compose) == 1:
    print(compose[0].upper())

else:
    for i in range(1, len(compose)):
        if word.count(biggest) < word.count(compose[i]):
            biggest = compose[i]
            index = i
            num.clear()
            num.append(compose[i])

        elif word.count(biggest) == word.count(compose[i]):
            num.append(compose[i])

    if len(num) > 1:
        print("?")

    else:
        print(num[0].upper())
