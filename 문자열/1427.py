target = str(int(input()))
lis = list(target)
lis.sort(reverse=True)
answer = ''.join(lis)

print(answer)