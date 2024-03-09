import sys
input = sys.stdin.readline

t_machine = list(map(int, input().split(':')))
hour = [i for i in range(1, 13)]
min_sec = [i for i in range(60)]

result = 0

for i in range(3) :
    for j in range(3) :
        for k in range(3) :
            if i != j and j != k and k != i :
                if t_machine[i] in hour and t_machine[j] in min_sec and t_machine[k] in min_sec :
                    result += 1

print(result)