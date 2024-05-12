n, m = map(int, input().split())

li = []
ans=''
nucleotides = ['A', 'C', 'G', 'T']
dist = 0

for i in range(n):
    dna = input()
    li.append(dna)
    
for i in range(m):
    a, c , g, t = 0, 0, 0, 0

    # 각 DNA의 첫번째 글자 비교하는 것.
    for j in range(n):
        if li[j][i]==nucleotides[0]:
            a += 1
            
        elif li[j][i]==nucleotides[1]:
            c += 1

        elif li[j][i]==nucleotides[2]:
            g += 1

        elif li[j][i]==nucleotides[3]:
            t += 1

    alpha = [a, c, g, t]
    selected = nucleotides[alpha.index(max(alpha))]
    ans += selected

    for k in range(n):
        if li[k][i] != selected:
            dist += 1 
        
print(ans)
print(dist)