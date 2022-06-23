def solution():
    while True:
        # 입력 받기
        n = int(sys.stdin.readline().strip())
    
        if not n: # 0을 입력 받을 경우 종료
            break
        revenues = [int(sys.stdin.readline().strip()) for _ in range(n)]
        
        # 알고리즘 시작 - DP
        result = [] # 결과 리스트
        for revenue in revenues:
            if not result: # 처음 경우
                result.append(revenue)
            else:
                result.append(max(result[-1] + revenue, revenue)) # 이전 까지의 값 + 현재 값 vs 현재 값 중에 큰 값(총합이 0보다 작아질 경우 다시 카운트)
                
        print(max(result))
    
solution()