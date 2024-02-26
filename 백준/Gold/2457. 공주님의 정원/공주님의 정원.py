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

while (idx < n):
    # 정원의 마지막 꽃이 지는 날짜가 12월 1일 이상이 됐거나,
    # 현재 확인할 꽃의 시작 날짜가 정원의 마지막 꽃이 지는 날짜와 이어지지 않을 경우, 탐색 종료
    if (end >= 1201 or arr[idx][0] > end):
        break

    # 꽃이 피는 날짜가 end 이전일 때, 가장 느리게 지는 꽃의 날짜
    temp_end_date = 0

    while (idx < n):
        # 꽃이 피는 날짜가 end 이전이라면,
        if (arr[idx][0] <= end):
            # 그 중 가장 느리게 지는 꽃의 날짜를 확인
            if (temp_end_date <= arr[idx][1]):
                temp_end_date = arr[idx][1]
            idx += 1
        else:
            idx -= 1
            break

    # 가장 꽃이 느리게 지는 날짜를 end_date로 수정
    end = temp_end_date
    # 심은 꽃의 개수 증가
    cnt += 1
    idx += 1

# 마지막으로 확인한 꽃의 지는 날짜가 12월 1일 보다 작으면, 
# 3월 1일부터 11월 30일까지 계속 피어있는게 아니므로 0 출력
if end < 1201:
    print(0)
else:
    print(cnt)