import sys
input = sys.stdin.readline

word = [0] + list(map(int, input().rstrip()))
if word[1] == 0: # 올바르지 않은 경우
    print(0)

else:
    size = len(word) - 1 # 위에서 의미없는 값을 하나 추가했기 때문

    dp = [0] * (size + 1)
    # 0, 1개의 숫자를 구성하는 경우의 수는 1이다.
    dp[0] = 1
    dp[1] = 1

    for i in range(2, size + 1):
        # 0인 경우는 반드시 앞 숫자와 붙어야함.
        if word[i] > 0:
            dp[i] += dp[i - 1]

        num = word[i - 1] * 10 + word[i]
        if num >= 10 and num <= 26 :
            dp[i] += dp[i - 2]

    print(dp[size] % 1_000_000)