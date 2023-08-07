import sys
input = sys.stdin.readline
sys.setrecursionlimit(10 ** 5)

n, k = map(int, input().split())

# 배울 알파벳이 5개 미만이면 0가지의 경우의 수
if k < 5:
    print(0)
    exit()

# 모든 알파벳을 전부 배울 경우 입력받은 단어를 모두 학습할 수 있다.
elif k == 26:
    print(n)
    exit()

#공용알파벳 antic을 비트마스킹
antic = 0
for alpha in 'antic':
    order = ord(alpha) - ord('a')
    antic |= 1 << order

# 입력받은 단어를 비트마스킹
words = []
for _ in range(n):
    word = input()
    b = 0
    for alpha in word:
        order = ord(alpha) - ord('a')
        b |= (1 << order)
    words.append(b)


# learn이 word의 비트를 포함하는가?
def check(learn):
    read = 0
    for j in range(n):
        word = words[j]
        if (learn & word) == word:
            read += 1
    return read

# 알파벳 a~z까지 순서대로 탐색한다.
def recursion(idx, learn):
    global answer
    # 알파벳 개수가 26개이다.
    if idx > 26:
        return

    # 단어에 알파벳이 k개가 들어갔을 때
    if bin(learn).count('1') == k:
        answer = max(answer, check(learn))
        return


    # 알파벳 a, n, t, i, c는 무조건 배워야하기 때문에, 선택하지 않는 경우는 고려하지 않는다.
    if antic & (1 << idx):
        recursion(idx + 1, learn | (1 << idx))

# 알파벳을 선택하거나, 선택하지 않거나 둘 중 한 가지 경우의 수를 매번 택할 수 있다.
    else:
        # 알파벳을 선택하지 않을 때
        recursion(idx + 1, learn)
        # 알파벳을 선택할 때
        recursion(idx + 1, learn | (1 << idx))

    return answer

answer = 0
print(recursion(0, 0))
elif k == 26:
    print(n)
else:
    dfs(0,0)
    print(ans)
