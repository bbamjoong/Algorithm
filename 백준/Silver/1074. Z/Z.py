import sys
input = sys.stdin.readline

n, r, c = map(int, input().split())

ans = -1
def check(x,y,size):
    global ans

    if x == r and y == c:
        ans += 1
        print(ans)
        exit()

    if size == 0:
        ans+=1
        return

    if not (x <= r <x+2**size and y<= c < y+2**size):
        ans += 2**(size*2)
        return


    check(x,y,size-1)
    check(x,y+2**(size-1), size-1)
    check(x+2**(size-1), y, size-1)
    check(x+2**(size-1), y+2**(size-1), size-1)

check(0,0,n)