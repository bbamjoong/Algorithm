import sys
input = sys.stdin.readline

size, num = map(int, input().split())
num = str(num)

x, y = map(int, input().split())


def find_idx(num, idx, r, c, n):
    global num_x, num_y

    if idx == len(num):
        num_x, num_y = r, c
        return

    if num[idx] == '1':
        find_idx(num, idx+1, r, c + 2**n//2, n-1)

    if num[idx] == '2':
        find_idx(num, idx+1, r, c, n-1)

    if num[idx] == '3':
        find_idx(num, idx+1, r + 2**n//2, c, n-1)

    if num[idx] == '4':
        find_idx(num, idx+1, r + 2**n//2, c + 2**n//2, n-1)


num_x, num_y = 0, 0
find_idx(num, 0, 0, 0, size)
ans_x, ans_y = num_x - y, num_y + x

ans = ''
def find_ans(x, y, n):
    global ans, ans_x, ans_y, size
    if n == 0:
        print(ans)
        exit()

    # 2사분면
    if (x <= ans_x < x + 2**n//2) and (y <= ans_y < y + 2**n//2):
        ans += '2'
        find_ans(x, y, n-1)

    # 1사분면
    if (x <= ans_x < x + 2**n//2) and (y + 2**n//2 <= ans_y < y + 2**n):
        ans += '1'
        find_ans(x, y + 2**n//2, n-1)

    # 3사분면
    if (x + 2**n//2 <= ans_x < x + 2**n) and (y <= ans_y < y + 2**n//2):
        ans += '3'
        find_ans(x + 2**n//2, y, n-1)

    # 4사분면
    if (x + 2**n//2 <= ans_x < x + 2**n) and (y + 2**n//2 <= ans_y < y + 2**n):
        ans += '4'
        find_ans(x + 2**n//2, y + 2**n//2, n-1)

if 0 <= ans_x < 2**size and 0 <= ans_y < 2**size:
    find_ans(0,0,size)

else:
    print(-1)