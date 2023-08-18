import sys
input = sys.stdin.readline

r,c,k = map(int, input().split())

arr = [list(map(int, input().split())) for _ in range(3)]

def check():
    # 그래프 행의 최대길이
    mx = 0
    for i in range(len(arr)):
        # 숫자가 어떤것이 있을까?
        nums = set(arr[i])    
        # (숫자, cnt)를 담을 배열
        li1 = []
        # 행 완성
        li2 = []

        for num in nums:
            # 0이면 지나친다.
            if num == 0:
                continue
            cnt = arr[i].count(num)
            li1.append([num,cnt])
        # cnt를 기준으로 오름차순 정렬 -> 숫자를 기준으로 오름차순 정렬
        li1.sort(key = lambda x:(x[1], x[0]))

        for num, cnt in li1:
            li2.append(num)
            li2.append(cnt)

        # 100개까지 저장하도록 하자
        arr[i] = li2[:100]

        # 그래프 행의 최대길이 저장
        mx = max(mx, len(arr[i]))
    
    # 0채우기
    for i in arr:
        while len(i) < mx:
            i.append(0)




for i in range(101):
    # 3 3 3       의 경우 1초 후 배열이
    # 1 1 1       1 2
    # 1 1 1       1 2    -> arr[2][2]가 없어 out of index 에러가 발생한다.
    # 1 1 1       1 2       따라서 try except를 통해 에러를 패싱해준다.
    try:
        if arr[r-1][c-1] == k:
            print(i)
            break
    except:
        pass

    # 행 개수 >= 열 개수
    if len(arr) >= len(arr[0]):
        check()

    # 열 검사 시 전치행렬로 전환
    else:
        arr = [list(x) for x in zip(*arr)]
        check()
        arr = [list(x) for x in zip(*arr)]

else:
    print(-1)