def prime(n):
    li = [0, 0] + [1] * n
    for i in range(2, n + 1):
        if li[i]:
            for j in range(2 * i, n + 1, i):
                li[j] = 0
    return li

li = prime(1000000)

for _ in range(int(input())):
    n = int(input())
    flag = 1
    for i in range(2, min(1000001, int(n ** 0.5) + 1)):
        if li[i] and not n % i:
            flag = 0
            break
    print('YES' if flag else 'NO')