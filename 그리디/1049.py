cut, brand = map(int, input().split())
package = []
one = []
price = []

for i in range(brand):
    pack, hole = map(int, input().split())
    package.append(pack)
    one.append(hole)

if cut % 6 != 0:
    price.append((((cut // 6) + 1) * min(package)))
    price.append(((cut // 6) * min(package)) + ((cut % 6) * min(one)))
    price.append(cut * min(one))
else:
    price.append((cut // 6) * min(package))
    price.append(cut * min(one))

print(min(price))

