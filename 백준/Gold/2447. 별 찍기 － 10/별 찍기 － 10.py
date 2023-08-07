import sys
input = sys.stdin.readline

n = int(input())
board = ["***", "* *", "***"]
cnt = 0


def stars(board):
    ans = []
    for i in range(3 * len(board)):
        if i // len(board) == 1:
            ans.append(board[i % len(board)] + " " * len(board) + board[i % len(board)])
        else:
            ans.append(board[i % len(board)] * 3)
    return ans


while n > 3:
    n /= 3
    cnt += 1

for i in range(cnt):
    board = stars(board)

for i in board:
    print(i)