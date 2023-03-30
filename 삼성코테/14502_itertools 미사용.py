def combination(arr, n):
    result = []
    if n == 0:
        return [[]]
    if n == 1:
        result = [[i] for i in arr]
        return result
    for i in range(len(arr)):
        elem = arr[i]
        c = combination(arr[i+1:], n-1)
        for rest in c:
            result.append([elem]+rest)
    return result


def permutation(arr, n):
    result = []
    if n == 0:
        return [[]]
    if n == 1:
        result = [[i] for i in arr]
        return result
    for i in range(len(arr)):
        elem = arr[i]
        p = permutation(arr[:i]+arr[i+1:], n-1)
        for rest in p:
            result.append([elem]+rest)
    return result


data = [1, 2, 3, 4]
print(permutation(data, 2))
print(combination(data, 2))