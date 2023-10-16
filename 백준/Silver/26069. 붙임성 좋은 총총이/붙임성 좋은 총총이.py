import sys
input = sys.stdin.readline

n = int(input())

dance = ['ChongChong']
for i in range(n):
    a, b = input().split()
    
    if a in dance and b not in dance:
        dance.append(b)

    elif a not in dance and b in dance:
        dance.append(a)

print(len(dance))