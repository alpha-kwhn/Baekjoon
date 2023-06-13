import sys
input = sys.stdin.readline

target = input()

check = []
nums = []
storage = ['[', '(', ']', ')']
flag = 1

for i in range(len(target)):
    print(check)
    if i == 0:
        if target[i] == ']' or target[i] == ')':
            flag = 0
            break
        else:
            check.append(target[i])

    elif i == len(target) - 1:
        if target[i] == '(' or '[':
            flag = 0
            break
        else:
            check.append(target[i])

    else:
        if target[i] == '[' or '(':
            if type(check[-1]) == int:
                nums.append(check[-1])
                check.pop()
            check.append(target[i])

        elif target[i] == ')':
            if check[-1] == '(':
                check.append(2)
                print(check)
            else:
                if type(check[-1]) == int and check[-2] == '(':
                    tmp = check.pop()
                    check.pop()
                    check.append(2 * tmp)
                else:
                    flag = 0
                    break

        elif target[i] == ']':
            if check[-1] == '[':
                check.append(3)
            else:
                if type(check[-1]) == int and check[-2] == '[':
                    tmp = check.pop()
                    check.pop()
                    check.append(3 * tmp)
                else:
                    flag = 0
                    break

sent = ''.join(check)

if sent == "()":
    for i in nums:
        i *= 2

elif sent == "[]":
    for i in nums:
        i *= 3

print(sum(nums))