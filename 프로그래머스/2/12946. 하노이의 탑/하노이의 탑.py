def hanoi(start, end, extra, n, answer):
        if n == 1:
            answer.append([start, end])

        else:
            hanoi(start, extra, end, n - 1, answer) 
            hanoi(start, end, extra, 1, answer) 
            hanoi(extra, end, start, n - 1, answer) 
        return answer 
        
def solution(n):
    answer = hanoi(1, 3, 2, n, [])
    return answer