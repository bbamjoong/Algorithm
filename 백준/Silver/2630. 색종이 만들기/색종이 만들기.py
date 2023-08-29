import sys
input = sys.stdin.readline

n = int(input())

arr = [list(map(int, input().split())) for _ in range(n)]

white = 0 # 숫자0
blue = 0 # 숫자1
def check(arr, length):
    global white, blue

    # 배열의 가장 왼쪽 & 위쪽의 숫자
    num1 = arr[0][0]
    stop = False

    for i in range(length):
        if stop:
            break
        for j in range(length):
            if arr[i][j] != num1:
                # 배열의 숫자가 모두 같지 않을 시 break
                stop = True
                break
    
    # 배열의 숫자가 모두 같을 때
    if stop == False:
        if num1 == 0:
            white += 1
        else:
            blue += 1
    
    # 배열의 숫자가 모두 같지 않을 때
    else:
        # 배열의 크기가 2 X 2일 경우는 숫자들을 모두 카운트해준다.
        if length == 2:
            for i in range(length):
                for j in range(length):
                    if arr[i][j] == 0:
                        white += 1
                    else:
                        blue += 1
                        
        # 그 외의 경우에는 구역을 나눠서 check를 해준다.
        else:
            check([arr[i][:length//2]for i in range(length//2)], length//2)
            check([arr[i][length//2:]for i in range(length//2)], length//2)
            check([arr[i][:length//2]for i in range(length//2, length)], length//2)
            check([arr[i][length//2:]for i in range(length//2, length)], length//2)

check(arr, n)
print(white)
print(blue)
