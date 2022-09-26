
import heapq, sys

def solution(lectures):
    # 강의 시작 시간을 기준으로 정렬
    lectures.sort(key=lambda x:x[1])
    # 배정된 강의를 넣는 리스트
    arr = []
    count = 0
    for lecture in lectures:
        # 강의가 가장 빨리 끝나는 시간보다 시작시간(lecture[1])이 더 크면 
        while arr and arr[0] <= lecture[1]:
            heapq.heappop(arr)
        # arr에 끝나는 시간(lecture[2])을 추가한다.
        heapq.heappush(arr, lecture[2])
        # 최대값 = 필요한 최소 강의실 수
        count = max(count, len(arr))
    print(count)

input = sys.stdin.readline
n = int(input())
lectures = [list(map(int, input().split())) for _ in range(n)]
solution(lectures)

