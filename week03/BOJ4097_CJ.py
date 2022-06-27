import sys
input = sys.stdin.readline

def maxProfit():
    while 1:
        N = int(input())

        #0을 입력받았을 때 break
        if N == 0:
            break
        else:
            profit = [int(input()) for _ in range(N)]

            for i in range(1,N):
                #최대 profit 구하기
                profit[i] = max(profit[i-1]+profit[i], profit[i])

            #print(profit)
            
            #최대 profit값 출력
            print(max(profit))

maxProfit()