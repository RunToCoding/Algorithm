# 입국심사

"""
n - 입국심사 기다리는 사람 수
times - 심사하는데 걸리는 시간

모든 사람이 심사 받는데 걸리는 시간 최솟값 return
"""

def solution(n, times):
    answer = 0
    if n == 1: return min(times)

    start, end = min(times), max(times)*n

    while start <= end:
        mid = (start+end) // 2
        checked = 0
        for time in times:
            checked += mid // time
            if checked >= n: 
                answer, end = mid, mid-1
                break # 중간 시간보다 심사관들이 받을 수 있는 인원수가 더 많으면 끝    
        if checked < n: #심사한 수가 받은 사람보다 적으면 시간 늘려서
            start = mid+1
    return answer

print(solution(6, [7, 10]))