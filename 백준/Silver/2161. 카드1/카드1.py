import sys
input = sys.stdin.readline

nums = int(input())
card = []
ans = []

for i in range(1, nums+1):
    card.append(i)

while (len(card) != 0):
    ans.append(card.pop(0))
    if(len(card) != 0):
        card.append(card.pop(0))

for j in ans:
    print(j, end =" ")