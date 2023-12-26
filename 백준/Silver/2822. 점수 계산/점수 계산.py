import sys
input = sys.stdin.readline

score = []

for i in range(8):
    score.append(int(input()))
    
tmp = []
answer = 0
for i in range(5):
    answer += max(score)
    tmp.append(score.index(max(score)) + 1)
    score[score.index(max(score))] = -1
    
tmp.sort()

print(answer)
print(*tmp)