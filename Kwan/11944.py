n, m = input().split()
c = 1
p = n * int(n)
q = int(m)

if len(p) > q:
    k = p[0:q]
    print(int(k))

else:
    print(int(p))