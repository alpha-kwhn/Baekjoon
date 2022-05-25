import sys
input = sys.stdin.readline

num = int(input())
lis = list(map(int, input().split()))

print(min(lis), max(lis))