import sys
input = sys.stdin.readline

n, k = map(int, input().split())

MAX = 0
# k의 최댓값을 찾는다.
for i in range(1, n//2+1):
    MAX = max(MAX, i*(n-i))

if k > MAX:
    print(-1)
    exit()


def check(word):
    cnt = 0
    for i in range(len(word)-1):
        if word[i] == 'A':
            for j in range(i+1,len(word)):
                if word[j] == 'B':
                    cnt+=1
    return cnt


word = ['B' for _ in range(n)]

# 앞에서부터 A를 순차적으로 채워나간다.
for i in range(n):
    word[i] = 'A'
    if check(word) == k:
        break
    # k의 개수보다 check(word)의 개수가 많다면 A를 뒤로 옮기는 전략을 취한다.
    elif check(word) > k:
        word[i] = 'B'
    # k의 개수보다 check(word)의 개수가 적다면 나중에 뒤쪽에 A를 추가해준다.
print(''.join(word))