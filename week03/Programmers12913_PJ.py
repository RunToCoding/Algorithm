def solution(land):
    row = len(land)

    if row == 1: return max(land) # 행이 1개인 경우

    for r in range(1, row):
        temp = land[r-1]
        
        land[r][0] += max(temp[1:])
        land[r][1] += max([temp[0]] + temp[2:])
        land[r][2] += max(temp[:2]+[temp[3]])
        land[r][3] += max(temp[:3])

    #print(land)
    return max(land[-1])

solution([[1,2,3,5],[5,6,7,8],[4,3,2,1]])