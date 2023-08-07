x = int(input())
list1 = []
for i in range(x):
    list1.append(int(input()))
list2 = sorted(list1)
for i in range(len(list1)):
    print(list2[i])