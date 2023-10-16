import sys
input = sys.stdin.readline

n = int(input())

dance = set(['ChongChong'])

for i in range(n):
	a, b = input().split()
	
	if a in dance:
		dance.add(b)
		
	if b in dance:
		dance.add(a)
		
print(len(dance))