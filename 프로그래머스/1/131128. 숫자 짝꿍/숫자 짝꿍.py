def solution(X, Y):
    # X와 Y에서 숫자의 빈도수를 각각 카운트하기 위한 딕셔너리
    count_X = {}
    count_Y = {}

    # X의 숫자 빈도수 카운트
    for x in X:
        if x in count_X:
            count_X[x] += 1
        else:
            count_X[x] = 1

    # Y의 숫자 빈도수 카운트
    for y in Y:
        if y in count_Y:
            count_Y[y] += 1
        else:
            count_Y[y] = 1

    answer = ''

    # 9 ~ 0 순으로 공통된 숫자를 찾아 추가
    for i in range(9, -1, -1):
        i = str(i)  # 숫자를 문자열로 변환
        if i in count_X and i in count_Y:
            answer += i * min(count_X[i], count_Y[i])

    # 빈 문자열일 경우 -1 반환
    if answer == '':
        return '-1'
    
    # 답이 0밖에 없는 경우 0 반환
    elif len(answer) == answer.count('0'):
        return '0'

    return answer