import sys
input = sys.stdin.readline

n = int(input())

arr = []
for _ in range(n):
    tmp = list(map(int, input().split()))
    arr.append([tmp[0] * 100 + tmp[1], tmp[2] * 100 + tmp[3]])

arr.sort()

end = 301
cnt = 0
idx = 0

while (idx < n): # 끝까지  조사
    # 다음 꽃의 시작 날짜가 end보다 크면 -> 빈 날짜가 존재하므로 break
    # end가 1201보다 크면 조기에 종료하면 된다.
    if (end >= 1201 or arr[idx][0] > end):
        break

    # 꽃이 피는 날짜가 end 이전일 때, 가장 느리게 지는 꽃의 날짜
    tmpEnd = 0
    while (idx < n):
        if (arr[idx][0] <= end):
            tmpEnd = max(tmpEnd, arr[idx][1]) # 가장 느리게 피는것
            idx += 1 # 다음 순회
        else:
            idx -= 1 # 없으면 idx - 1  => 뒤에서 idx + 1 해주니까 다른 조건으로 방문하게
            break

    end = tmpEnd
    cnt += 1
    idx += 1

if end < 1201:
    print(0)
else:
    print(cnt)