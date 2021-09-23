total = int(input())
network = int(input())

jip = [1]
dic = {}

for i in range(network):
    tmp = list(map(int, input().split()))
    if tmp[0] in dic:
        dic[tmp[0]] += [tmp[1]]
    else:
        dic[tmp[0]] = [tmp[1]]

def BFS(index, dic):
    if index in dic:
        for i in range(len(dic[index])):
            if dic[index][i] not in jip:
                jip.append(dic[index][i])
            BFS(dic[index][i], dic)
    return len(jip) - 1

print(BFS(1, dic))








