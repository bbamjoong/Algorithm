N = int(input())
card_price = [0] + list(map(int, input().split()))
prices = [0 for _ in range(N + 1)]

for i in range(1, N + 1):
    temp = [prices[j] + card_price[i - j] for j in range(i)]
    prices[i] = min(temp)

print(prices[N])