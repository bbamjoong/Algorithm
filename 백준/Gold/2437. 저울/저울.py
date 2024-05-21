import sys
input = sys.stdin.readline

n = int(input())
li = list(map(int, input().split()))
li.sort()

# 정렬 -> 무게추를 하나씩 더하면서 범위를 지정해줌.
ans = 1 # 답의 최소 값은 1임.

# 만약 내가 1~5까지 탐색가능함.
# 그런데 다음 추가 3이라고 하면
# 1~5 => 3~8  따라서 1~8까지 탐색 가능하겠지.

# 그런데 다음 추가 6이라고 하면 가능하겠지.
# 하지만 다음 추가 7이라고 하면 불가능하다.
for num in li:
    if ans < num:
        break

    ans += num

print(ans)