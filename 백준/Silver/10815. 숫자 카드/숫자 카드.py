import sys
input = sys.stdin.readline

#딕셔너리 생성
hash = {}

n = int(input())
arr1 = list(map(int, input().split()))
m = int(input())
arr2 = list(map(int, input().split()))

#만약 원소가 딕셔너리에 없다면 value를 1로 추가
#만약 원소가 딕셔너리에 있다면 value에 1을 더해줌
for i in arr1:
    if i in hash :
        hash[i] += 1
    else:
        hash[i] = 1

for i in arr2:
    if i in hash:        
        print(hash[i], end = ' ')
    else:
        print(0, end=' ')