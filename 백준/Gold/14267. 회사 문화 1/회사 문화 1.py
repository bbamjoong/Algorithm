import sys
input = sys.stdin.readline

n, m = map(int,input().split())
boss = [0] + list(map(int,input().split()))
ans = [0] * (n+1)

# 본인이 칭찬을 받았으니 일단 더해줌.        
for _ in range(m):
    index,weight = map(int, input().split())
    ans[index] += weight
    
# 1번은 사장이니까, 2번부터 칭찬을 받을 수 있음. 사장 -> 본부장 가능.    
# 상사들 부터 칭찬 받은 weight를 더해주게 되면 부하들은 자연스럽게 weight가 더해짐.
for i in range(2, n+1):
# 해당 사람의 보스의 ans를 더해줌. 보스가 칭찬을 받았으면 자연스레 더해지는거고, 보스가 칭찬을 받지 않았다면 아무것도 더해지지 않는 거지.
    ans[i] += ans[boss[i]]
            
print(*ans[1:])