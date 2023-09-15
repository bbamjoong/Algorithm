import sys
input = sys.stdin.readline

n, b = map(int, input().split())

arr = [list(map(int, input().split()))for _ in range(n)]

#행렬 곱 함수
def mul(matrix1, matrix2):
    global n
    mat = [[0] * n for _ in range(n)]
    
    for row in range(n):
        for col in range(n):
            sm = 0
            for i in range(n):
                sm += matrix1[row][i] * matrix2[i][col]
            mat[row][col] = sm % 1000
    return mat

# n제곱 함수
def square(matrix, n):
    if n == 1:
        return arr
    tmp = square(matrix, n//2)
    if n % 2:
        return mul(mul(tmp, tmp), arr)
    else:
        return mul(tmp, tmp)

if b == 1:
    for i in arr:
        for j in i:
            print(j%1000, end=' ')
        print()
else:    
    ans = square(arr, b)

    for i in ans:
        print(*i)