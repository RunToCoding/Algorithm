import sys
input = sys.stdin.readline


def main():
    while True:
        N = int(input())
        if N == 0:  break   # 테스트케이스 종료

        profit = [int(input()) for _ in range(N)]
        print(solution(N, profit))


def solution(N, profit):
    result = [0 for _ in range(N)]  # result[i]에 profit[0]부터 profit[i]까지의 최대 합을 저장하는 list
    result[0] = profit[0]           # 초깃값 : 0번째까지의 최대 합은 profit[0]

    for i in range(1, N):   # result[i] 계산
        if result[i - 1] > 0:   # 이전까지의 최대 합(result[i - 1])이 양수면 result[i - 1] + profit[i] 가 최대
            result[i] = result[i - 1] + profit[i]
        else:                   # 이전까지의 최대 합(result[i - 1])이 음수면 profit[i] 가 최대
            result[i] = profit[i]

    return max(result)


main()
