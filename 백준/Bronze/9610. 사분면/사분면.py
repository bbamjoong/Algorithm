import sys
input=sys.stdin.readline

n = int(input().strip())
Q1 = 0
Q2 = 0
Q3 = 0
Q4 = 0
axis = 0
for i in range(n):
    a, b = map(int, input().split())
    if a>0 and b>0:
        Q1+=1
    elif a>0 and b<0:
        Q4+=1
    elif a<0 and b<0:
        Q3+=1
    elif a<0 and b>0:
        Q2+=1
    else:
        axis+=1

print('Q1:',Q1)
print('Q2:',Q2)
print('Q3:',Q3)
print('Q4:',Q4)
print('AXIS:',axis)
