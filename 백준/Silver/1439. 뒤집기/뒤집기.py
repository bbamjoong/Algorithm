string = str(input())

arr_1 = string.split("1")
arr_0 = string.split("0")

res_1 = 0
res_0 = 0

for i in arr_1:
    if "0" in i:
        res_1 += 1

for i in arr_0:
    if "1" in i:
        res_0 += 1

print(min(res_1, res_0))
