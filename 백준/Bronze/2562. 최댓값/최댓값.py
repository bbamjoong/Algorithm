import sys
num_list = []
for i in range(9):
    num_list.append(int(sys.stdin.readline()))

print(max(num_list))
print(num_list.index(max(num_list))+1)