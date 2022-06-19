N = int(input())
maxN = 9876543210 # 9876543210

if N <= 10 : print(N-1) # 한자리는 줄어드는 수 (0~9)
else: # 두자리 이상은 줄어드는 수 확인
    numInfo = [
        [[], [], [], [], [], [], [], [], [], []], #pre
        [[], [], [], [], [], [], [], [], [], []] #cur
    ]
    numInfoCount = [
        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0], #pre
        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0] #cur
    ]
    
    digit = 2 # 자릿수 확인
    curN = 10
    stop = False
    curPoint = 0

    while True:
        
        for i in range(digit-1, 10): 
            if digit == 2: # 두자리 수 일 경우
                if curN+i < N: # 찾는 숫자가 범위에 없으면 경우의 수만 추가
                    numInfo[curPoint][i] = [10*i + k for k in range(i)]
                    curN += i
                    numInfoCount[curPoint][i] = i#curN-10
                else: # 찾는 숫자가 범위에 있으면 N-curN-1이 내가 찾는 번째 수
                    print(10*i + N-curN-1) # 찾은 수 찾았으면 출력
                    stop=True
                    break

            else: # 그 이상 자리수일 경우
                # digit=3이면, 210부터 시작이므로 digit-1부터 시작, 이때 만들 수 있는 경우의 수는 두자리 수 정보에서 더 해감
                for j in range(digit-2, i): # digit=3, i=3 range(1, 3) -> 3 _ _ 부터 시작, _ _는 numInfoPre가져오기
                    if curN+numInfoCount[pre][j] < N: # 찾는 숫자가 범위에 없으면 경우의 수만 추가
                        numInfo[cur][i] += [10**(digit-1)*i + num for num in numInfo[pre][j]]
                        curN += numInfoCount[pre][j]
                        numInfoCount[cur][i] += numInfoCount[pre][j]

                        if numInfo[cur][i][-1] > maxN: 
                            print(-1)
                            stop=True
                            break
                                              
                    else: # 경우의 수 찾아서 출력
                        print(10**(digit-1)*i + numInfo[pre][j][N-curN-1])
                        stop=True
                        break
                
                if stop: break
        else: 
            digit += 1
            if not curPoint : curPoint = 1
            else: curPoint = 0

            pre, cur = 0 if curPoint else 1, curPoint
            numInfoCount[cur] = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
   
        if stop: break
    
