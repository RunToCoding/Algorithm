
while True:
    N = int(input())

    if N == 0 : break # 끝
    if N == 1 : print(int(input()))
    else:
        # 입력 값
        max_ = -10000
        money = []
        for _ in range(N):
            money.append(int(input()))

        temp = [money[0]] + [0]*(N-1)

        print(money)
        print(temp)

        for i in range(1, N):
            if temp[i-1]+money[i] < money[i]:
                temp[i] = money[i]
            else:
                temp[i] = temp[i-1] + money[i]
            
            max_ = max(max_, temp[i])

            print(i,"번째 계산중 : ", temp)
            print(i,"번째 max : ", max_)

    print("########next#########")
    
