import decimal
context = decimal.getcontext()
context.rounding = decimal.ROUND_HALF_UP

a = int(input())
hak = ['A+', 'A0', 'A-', 'B+', 'B0', 'B-', 'C+', 'C0', 'C-', 'D+', 'D0', 'D-', 'F']
jum = [4.3, 4.0, 3.7, 3.3, 3.0, 2.7, 2.3, 2.0, 1.7, 1.3, 1.0, 0.7, 0.0]
answer = 0.0
global grade
global point
score = 0

for i in range(0, a):
    subject, point, grade = input().split()
    real_point = int(point)
    score = score + real_point
    for j in range(0, 13):
        if(grade == hak[j]):
            answer = answer + (real_point * jum[j])

correct = str(answer / score)
print(round(decimal.Decimal(correct), 2))












