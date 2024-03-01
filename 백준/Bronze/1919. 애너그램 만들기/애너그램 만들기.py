li = [0]*26

for i in list(input()):
    li[ord(i) - 97] += 1
for j in list(input()):
    li[ord(j) - 97] -= 1

print(sum(map(abs, li)))