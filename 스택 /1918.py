question = str(input())
stack = []
important = {'+': 1, '-': 1, '*': 2, '/':2}
compute = []
answer = []

for item in question:
    if item.isalnum():
        answer.append(item)
    elif item == '(':
        stack.append(item)
    elif item == ')':
        while stack and stack[-1] != '(':
            answer.append(stack.pop())
        stack.pop()
    else:
        while stack and stack[-1] != '(' and important[item] <= important[stack[-1]]:
            answer.append(stack.pop())
        stack.append(item)

while stack:
    answer.append(stack.pop())

print(''.join(answer))










compute = [itm[0] for itm in compute]
target = stack + compute
answer += target

print(''.join(answer))