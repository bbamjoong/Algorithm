def solution(n, money):
    dp = [1] + [0] * n
    
    for type in money:
        for price in range(type, n + 1):
            if price >= type:
                dp[price] += dp[price - type]
    
    return dp[n] % 1_000_000_007