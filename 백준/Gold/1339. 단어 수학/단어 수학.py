import sys
from collections import deque
input = sys.stdin.readline

def solution():
    for i in range(n):    
        word = input().strip()
        for j in range(len(word)):
            arr[ord(word[j]) - 65] += 10 ** (len(word) - j - 1)
    
    arr.sort(reverse = True)
    ans = 0
    num = 9

    for i in range(len(arr)):
        if arr[i] !=0:
            ans+=arr[i]*num
            num -= 1
    print(ans)

n = int(sys.stdin.readline())
arr = [0 for _ in range(26)]
solution()