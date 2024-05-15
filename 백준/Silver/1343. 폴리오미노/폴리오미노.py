import sys
input = sys.stdin.readline

## 4개는 AAAA로 바꿔줌.
b = input().rstrip()
b = b.replace("XXXX", "AAAA")
b = b.replace("XX", "BB")

## 바꿔야할게 남아있으면 -1 출력
if 'X' in b:
    print(-1)
else:
    print(b)