# 땅따먹기

def solution(land):
    len_ = len(land)
    
    for i in range(1, len_):
        temp = land[i-1]
        land[i][0] += max(temp[1:])
        land[i][1] += max(temp[0], max(temp[2:]))
        land[i][2] += max(max(temp[:2]), temp[3])
        land[i][3] += max(temp[:3])

    return max(land[-1])

print(solution([[1,2,3,5],[5,6,7,8],[4,3,2,1]]))