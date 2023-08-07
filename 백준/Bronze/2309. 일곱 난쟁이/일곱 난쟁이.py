import sys
input= sys.stdin.readline
li1 = []
for i in range(9):
    li1.append(int(input()))
li1.sort()
sum1 = sum(li1)
for i in range(8):
    for j in range(i+1,9):
        if sum1 - (li1[i] + li1[j]) == 100:
            li1.pop(i)
            li1.pop(j-1)
            break
    if len(li1) == 7:
        break
for i in range(7):
    print(li1[i])