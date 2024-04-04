t = int(input())

for tc in range(1, t + 1):
    n, b = map(int, input().split())
    arr = list(map(int, input().split()))


    def subset(idx, sm):
        global ans, b
        if sm >= ans:
            return

        if idx == n:
            if sm >= b:
                ans = min(ans, sm)
            return

        subset(idx + 1, sm)  # 안더해줌
        subset(idx + 1, sm + arr[idx])  # 현재 값 더해줌


    ans = int(1e9)
    subset(0, 0)
    print(f"#{tc} {ans - b}")