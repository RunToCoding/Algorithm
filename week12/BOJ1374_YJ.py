import sys
from heapq import heappop, heappush
input = sys.stdin.readline

def main():
    n = int(input())
    print(solution(n))

def solution(n):
    heap = []
    for _ in range(n):
        lecNum, startTime, endTime = map(int, input().split())
        heappush(heap, [startTime, endTime, lecNum])  # 강의 시작 시간을 기준으로 힙에 추가

    # 초깃값
    roomQueue = []
    startTime, endTime, lecNum = heappop(heap)  # 가장 먼저 시작하는 강의
    heappush(roomQueue, endTime)                # 의 끝나는 시간을 큐에 삽입

    while heap: # 힙에 강의가 남아 있는 동안
        startTime, endTime, lecNum = heappop(heap)
        if roomQueue[0] <= startTime:   # 첫 번째 강의가 끝난 후에 시작되는 강의인 경우 동시에 들어갈 필요가 없으므로
            heappop(roomQueue)          # 강의실에서 강의를 제거하고
        heappush(roomQueue, endTime)    # 새로운 강의 추가

    return len(roomQueue)

main()