import queue as q

def solution(food_times, k):
    pq = q.PriorityQueue()
    
    for i in range(len(food_times)):
        pq.put([food_times[i], i + 1])
    
    small = pq.queue[0][0]
    prev = 0
    
    # 가장 작은 것 부터 점점 값을 키움. 따라서 small - prev만큼 빼주면서 사이클을 돌린다.
    while k - ((small - prev) * pq.qsize()) >= 0:
        # k 값 최신화
        k -= (small - prev) * pq.qsize()
        
        # small로 이용했던 값을 prev로 변경
        prev, idx = pq.get()
        
        # 만약 값 하나만 남았다면 -1
        if pq.empty():
            return -1
        
        # pq의 가장 앞쪽 값을 small로
        small = pq.queue[0][0]
    
    pq.queue = sorted(pq.queue, key=lambda x:x[1])
    return pq.queue[k % len(pq.queue)][1]