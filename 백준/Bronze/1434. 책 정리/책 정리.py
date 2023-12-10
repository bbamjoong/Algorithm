N, M = map(int, input().split())
box = list(map(int, input().split()))
book = list(map(int, input().split()))
i = j = t = in_box = 0
while i < N and j < M:
    if box[i] < t+book[j]:
        t = 0
        i += 1
    else:
        in_box += book[j]
        t += book[j]
        j += 1
print(sum(box)-in_box)    