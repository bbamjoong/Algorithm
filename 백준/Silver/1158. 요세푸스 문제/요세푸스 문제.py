N,K=map(int,input().split())
a=[i for i in range(1,N+1)]
num=0
print("<",end="")
for i in range(N):
    num+=K-1
    if(num>=len(a)):
        num=num%len(a)
    if(i==N-1):
        print(a[num],end="")
    else:
        print(a[num],end=", ")
    del a[num]
print(">",end="")