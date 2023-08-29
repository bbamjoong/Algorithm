import sys
input = sys.stdin.readline

n = int(input())

arr = [list(map(int, input().split())) for _ in range(n)]

white = 0 # 숫자0
blue = 0 # 숫자1
def check(arr, length):
    global white, blue

    num1 = arr[0][0]
    stop = False

    for i in range(length):
        if stop:
            break
        for j in range(length):
            if arr[i][j] != num1:
                stop = True
                break
    
    if stop == False:
        if num1 == 0:
            white += 1
        else:
            blue += 1
    
    else:
        if length == 2:
            for i in range(length):
                for j in range(length):
                    if arr[i][j] == 0:
                        white += 1
                    else:
                        blue += 1
        
        else:
            check([arr[i][:length//2]for i in range(length//2)], length//2)
            check([arr[i][length//2:]for i in range(length//2)], length//2)
            check([arr[i][:length//2]for i in range(length//2, length)], length//2)
            check([arr[i][length//2:]for i in range(length//2, length)], length//2)

check(arr, n)
print(white)
print(blue)