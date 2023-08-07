import sys
input = sys.stdin.readline
#핵심 : 어차피 각 행마다 퀸을 하나씩 두기 때문에 열, 대각선 두개만 체크하면 된다.
N = int(input())
#열 체크
col_check = [0]*N
#체스판의 / 대각선 개수 : 2*n - 1
diag_check1 = [0]*(2*N-1)
#체스판의 \ 대각선 개수 : 2*n - 1
diag_check2 = [0]*(2*N-1)

ans = 0

def queen(i):
    global ans
    if i == N:
        ans += 1
        return
    for j in range(N):
        #ex) (2,3) (1,4)는 같은 대각선에 위치한다. -> i + j 가 같다.
        #ex) (2,3) (1,2)는 같은 대각선에 위치한다. -> j - i or i - j가 같다.
        if col_check[j]==0 and diag_check1[i+j]==0 and diag_check2[j-i]==0:
            col_check[j] = 1
            diag_check1[i+j] = 1
            diag_check2[j-i] = 1
            queen(i+1)
            col_check[j] = 0
            diag_check1[i+j] = 0
            diag_check2[j-i] = 0
queen(0)
print(ans)