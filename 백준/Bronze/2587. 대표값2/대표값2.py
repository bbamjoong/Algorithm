list1=[]
for i in range(5):
    list1.append(int(input()))
avg = int(sum(list1)/len(list1))
mean = sorted(list1)[2]
print(avg)
print(mean)