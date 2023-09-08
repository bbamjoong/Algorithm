import sys
input = sys.stdin.readline


n = int(input())
matrix = [[1, 1], [1, 0]]

# 행렬 곱셈
def mul_matrix(mat1, mat2):
    res = [[0]*2 for _ in range(2)]
    for i in range(2):
        for j in range(2):
            for z in range(2):
                res[i][j] += mat1[i][z] * mat2[z][j] % 1000000007
    return res

# 분할정복
def power(matrix, n):
    if n == 1:
        return matrix
    else:
        tmp = power(matrix, n // 2)

        # ex)n = 6  -> matrix ** 3 X matrix ** 3
        if n % 2 == 0:
            return mul_matrix(tmp, tmp)
        # ex)n = 7 -> matrix ** 6 X matrix ** 1
        else:
            return mul_matrix(mul_matrix(tmp, tmp), matrix)

ans = power(matrix, n)

# 출력
print(ans[0][1] % 1000000007)