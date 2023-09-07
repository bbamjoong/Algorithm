import sys
input = sys.stdin.readline

# 추천 : https://velog.io/@emplam27/%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-%EA%B7%B8%EB%A6%BC%EC%9C%BC%EB%A1%9C-%EC%95%8C%EC%95%84%EB%B3%B4%EB%8A%94-LCS-%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-Longest-Common-Substring%EC%99%80-Longest-Common-Subsequence#%EA%B5%AC%ED%98%84%EA%B3%BC%EC%A0%95-1

word1 = " " + input().rstrip()
word2 = " " + input().rstrip()

arr = [[0] * len(word2) for _ in range(len(word1))]

for i in range(1,len(word1)):
    for j in range(1,len(word2)):
        if word1[i] == word2[j]:
            arr[i][j] = arr[i-1][j-1] + 1
        
        else:
            arr[i][j] = max(arr[i-1][j], arr[i][j-1])


ans = 0
for i in arr:
    for j in i:
        ans = max(ans, j)

print(ans)