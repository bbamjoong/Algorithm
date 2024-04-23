import sys
input = sys.stdin.readline

N = int(input())
number_list = list(map(int, input().split()))

happiness_score = sum([1 if number % 2 == 0 else -1 for number in number_list])

print('Happy') if 0 < happiness_score else print('Sad')