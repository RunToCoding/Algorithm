def solution(land):
    # 각 경로의 최대 값을 구하는 결과
    result = []
    
    # 열 차례대로 진행하면서 각 위치에서의 최댓값 구하기
    for i, grounds in enumerate(land):
        if i == 0: # 첫번째 열일 경우
            result.append(grounds)
        else:
            tempResult = [] # 해당 열의 값을 저장하는 변수
            for j, ground in enumerate(grounds):
                save, result[-1][j] = result[-1][j], 0 # 이전 결과에서의 현재 행(j)의 값을 0으로 변경
                tempResult.append(ground + max(result[i-1])) # 그 최댓값을 현재 땅의 값과 더하여 추가
                result[i-1][j] = save
            
            result.append(tempResult) # 결과 맨 마지막으로 추가
    
    return max(result[-1]) # 최종 결과의 마지막 열에서의 최댓값 반환