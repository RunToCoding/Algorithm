def solution(land):
    #행의 개수
    rows = len(land)
    if rows == 1:
        return max(land[0])
    
    #차례대로 행 계산
    for i in range(1, rows):
        land[i][0] += max(land[i-1][1], land[i-1][2], land[i-1][3])     #2행 1열
        land[i][1] += max(land[i-1][0], land[i-1][2], land[i-1][3])     #2행 1열
        land[i][2] += max(land[i-1][0], land[i-1][1], land[i-1][3])     #2행 1열
        land[i][3] += max(land[i-1][0], land[i-1][1], land[i-1][2])     #2행 1열
        
        #print(land)
    #최대값 출력        
    return max(land[-1])

#입력
solution([[1,2,3,5],[5,6,7,8],[4,3,2,1]])