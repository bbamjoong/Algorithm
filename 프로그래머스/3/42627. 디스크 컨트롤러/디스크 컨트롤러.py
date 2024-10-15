import heapq

def solution(jobs):
    answer = 0
    now = 0
    i = 0
    start = -1
    heap = []
    
    while i < len(jobs):
        # 현재 시간과 겹치는 작업이 있다면 heap에 push
        for job in jobs:
            if start < job[0] <= now:
                heapq.heappush(heap,[job[1],job[0]]) # 최소 힙
        
        if heap:
            current = heapq.heappop(heap)
            start = now
            now += current[0]
            answer += now - current[1] # 지금에서 요청시간
            i += 1
            continue
        
        now += 1
            
    return answer // len(jobs)