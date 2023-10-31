lis = str(input())
stack = []
over = False

for item in lis:
    compute = 0
    if item in "([":
        stack.append(item)
    elif item == ")":
        if len(stack) > 0:
            while stack[-1] != "(" and len(stack) > 0:
                if stack[-1] in [")", "[", "]"]:
                    over = True
                    break
                else:
                    target = stack.pop()
                    compute += int(target)
                    if len(stack) == 0:
                        break
            if len(stack) == 0:
                over = True
                break
            else:
                stack.pop()
                if compute > 0:
                    stack.append(compute * 2)
                else:
                    stack.append(2)
        else:
            over = True
            break
    elif item == "]":
        if len(stack) > 0:
            while stack[-1] != "[" and len(stack) > 0:
                if stack[-1] in ["]", "(", ")"]:
                    over = True
                    break
                else:
                    target = stack.pop()
                    compute += int(target)
                    if len(stack) == 0:
                        break
            if len(stack) == 0:
                over = True
                break
            else:
                stack.pop()
                if compute > 0:
                    stack.append(compute * 3)
                else:
                    stack.append(3)
        else:
            over = True
            break

if over is True:
    print(0)
else:
    if "(" in stack or ")" in stack or "[" in stack or "]" in stack:
        print(0)
    else:
        print(sum(stack))



