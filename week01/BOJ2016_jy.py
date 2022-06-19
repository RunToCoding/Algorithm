from itertools import permutations

T = int(input())

CheckOrder = -1

def CompareLove(loveMW, checkCoupleM, checkCarW, checkStack, i):
    if not checkCoupleM[loveMW[i-1][checkCarW[i]]] : # 아무도 선택한 사람 x면
        checkCoupleM[loveMW[i-1][checkCarW[i]]] = i
    else: #선택한 사람이 이미 있으면, 남자 선호도 비교
        manNum = loveMW[i-1][checkCarW[i]]-1
        already, now = checkCoupleM[loveMW[i-1][checkCarW[i]]], i
        print("alredy, now, manNum", already, now, manNum)
        alreadyIdx, nowIdx = -1, -1
        for j in range(5):
            if loveMW[manNum][j] == already : 
                alreadyIdx = j
            elif loveMW[manNum][j] == now : 
                nowIdx = j
        print("idx 확ㅇ니 : ", alreadyIdx, nowIdx)
        if alreadyIdx < nowIdx:
            checkStack.append(now)
            checkCarW[now] += 1 # 차인 여자의 인덱스 up -> 다음 남자 고를 수 있게
        else:
            checkStack.insert(0, already)
            checkCarW[already] += 1 # 차인 여자의 인덱스 up -> 다음 남자 고를 수 있게
            checkCoupleM[manNum+1] = now # 남자 짝궁 선정
        
    return checkCoupleM, checkCarW, checkStack

def makeCouple(loveMW, checkCoupleM, checkCarW, checkStack):
    global CheckOrder
    print("###########start###########")
    print("first 선호도 : ", loveMW)
    for i in range(5, 10): # 여자-남자 선호도
        checkCoupleM, checkCarW, checkStack = CompareLove(loveMW, checkCoupleM, checkCarW, checkStack, i+1)
    print("first 미팅 결과 : ", checkCoupleM, checkCarW, checkStack)
    print("first 끝나고 차인 사람 : ", checkStack)
    
    print("########차인사람 미팅 이어서-->#########")
    while checkStack:
        CarWNum = checkStack.pop(-1)
        checkCoupleM, checkCarW, checkStack = CompareLove(loveMW, checkCoupleM, checkCarW, checkStack, CarWNum)
        print(" 차인사람 (",CarWNum, ") 미팅 결과 : ", checkCoupleM, checkCarW, checkStack)
        print("################다음미팅시작##########")
    
    if CheckOrder == -1 : 
        CheckOrder = checkCoupleM[1]
        return 1
    elif CheckOrder > CheckOrder: 
        return 0
    

for i in range(T):
    loveMW = [[0, 0, 0, 0, 0]]

    checkCoupleM = {
        1 : 0,
        2 : 0,
        3 : 0,
        4 : 0, 
        5 : 0
    }
    checkCarW = {
        6 : 0,
        7 : 0,
        8 : 0,
        9 : 0,
        10 : 0
    }

    checkStack = []

    for j in range(9):
        loveMW.append(list(map(int, input().split())))

    for permu in permutations([6, 7, 8, 9, 10], 5):
        loveMW[0] = list(permu)

        
        # 태현의 선호도들을 모두 만들어 for문 돌리기
        #print("man : ", loveMW)

        if not makeCouple(loveMW, checkCoupleM, checkCarW, checkStack):
            break
            print("NO")
    else:
        print("YES")

    print()

    break

