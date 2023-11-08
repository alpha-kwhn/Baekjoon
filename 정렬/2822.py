quiz = []

for i in range(8):
    ti = int(input())
    quiz.append((ti, i+1))

quiz = sorted(quiz, key=lambda x:-x[0])
quiz = quiz[:5]
sums = 0

for item in quiz:
    sums += item[0]

print(sums)

quiz = sorted(quiz, key=lambda x:x[1])

for k in quiz:
    print(k[1], end= " ")

