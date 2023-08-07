import sys
input = sys.stdin.readline

row, col = map(int, input().split())

# 행이 1개이면 못이동함
if row == 1:
    print(1)
# 행이 2개일 경우 오른쪽으로 두칸씩밖에 이동못함
elif row == 2:
    print(min(4, (col+1)//2))
# 행이 3개이상일 경우 / 열이 1~3개라면
elif 1 <= col <= 3:
    print(col)
# 행이 3개이상일 경우 / 열이 4~6개라면    
elif 4 <= col <= 6:
    print(4)
# 행이 3개이상일 경우 / 열이 6개 초과라면
# 4가지의 경우로 이동할때 필요한 열의 수는 7개이다
# 따라서 그 뒤로는 오른쪽으로 1칸씩만 이동하면 된다
else:
    print(col-2)