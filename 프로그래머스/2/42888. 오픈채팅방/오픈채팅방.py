def solution(record):
    answer = []
    dic = {}
    
    for rec in record:
        rec_split = rec.split()
        
        # 채팅방에 들어오거나(닉네임을 변경해 들어올 수 있음)
        # 채팅방에서 닉네임을 변경한 경우 => uid의 닉네임을 저장합니다.
        if len(rec_split) == 3:
            dic[rec_split[1]] = rec_split[2]
    
    for rec in record:
        rec_split = rec.split()
        
        if rec_split[0] == 'Enter':
            answer.append(f'{dic[rec_split[1]]}님이 들어왔습니다.')
            
        if rec_split[0] == 'Leave':
            answer.append(f'{dic[rec_split[1]]}님이 나갔습니다.')
            
    return(answer)