import sys
input = sys.stdin.readline

word = input().rstrip()
explode = input().rstrip()

stack = []

"""
1. stack에 문자열 추가
2. 폭발문자열 마지막 글자에 해당하는 문자가 stack에 추가 시
3. stack의 맨 뒷부분에 폭발문자열이 존재하면?
4. stack에서 문자열 삭제
시간복잡도 O(N), 공간복잡도 O(N).
"""
for char in word:
    stack.append(char)
    if char == explode[-1] and ''.join(stack[-len(explode):]) == explode:
        del stack[-len(explode):]

ans = ''.join(stack)

if ans =='':
    print("FRULA")
else:
    print(ans)