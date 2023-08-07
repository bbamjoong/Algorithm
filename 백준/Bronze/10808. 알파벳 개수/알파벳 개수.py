word=input()
cnt=[0]*26
for i in word:
    cnt[ord(i)-97]+=1
print(*cnt)