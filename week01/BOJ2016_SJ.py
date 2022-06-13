import itertools

def solution(priorityList, taehyun, prePriority, prePair):
    prePriority.append(taehyun)
    # print(priorityList, taehyun, prePriority, prePair)
    
    # 여자, 남자 우선순위
    priorityMan = [taehyun] + priorityList[:4]
    priorityWoman = priorityList[4:]
    # print(priorityMan)
    # print(priorityWoman)
    
    # 변수 선언
    pair = dict.fromkeys((1, 2, 3, 4, 5), 0)    # {남:여} 매칭 변수 (0은 매칭 안됨)
    noPairWoman = [0, 1, 2, 3, 4]               # 짝이 없는 여자 리스트(매턴에서 확인할 여자 리스트)
    pairPriorityIdx = [0, 0, 0, 0, 0]           # 각 여자들이 확인 해야할 우선순위 인덱스
    arrow = []
    
    # 짝 매칭하기
    while(0 in pair.values()):                                    # 0이 없을 때까지 반복(모두 매칭 상태)
        for i, priority in enumerate(priorityWoman):              # 여자 차례대로 반복
            if i in noPairWoman:                                  # 짝이 없는 여자의 경우 실행
                checkMan = priority[pairPriorityIdx[i]]           # 확인할 남자
                originWoman = pair[checkMan]                      # 확인할 남자의 기존 여자
                curWoman = 6 + i                                  # 현재 확인하는 여자
                if checkMan == 1:
                    arrow.append(curWoman)
                # print("현재 : {0:2,d} -> {1:2,d}".format(curWoman, checkMan))
                
                # 짝이 없는 남자인 경우
                if not originWoman:                               
                    # 남자에게 짝 추가
                    pair[checkMan] = curWoman
                    noPairWoman.remove(i)
                    # print(pair)
                else: # 짝이 있는 남자인 경우
                    if priorityMan[checkMan-1].index(originWoman) < priorityMan[checkMan-1].index(curWoman): # 기존 여자의 우선순위가 높을 때
                        pairPriorityIdx[i] += 1
                    else: # 기존 여자의 우선순위가 낮을 때
                        # 확인할 남자의 짝 변경 및 기존 여자를 확인할 여자 리스트에 추가
                        pair[checkMan] = curWoman
                        noPairWoman.remove(i)
                        noPairWoman.append(originWoman-6)
                        pairPriorityIdx[originWoman-6] += 1
                        # print(pair)
    # print(arrow)
    # print(pair[1])
    if prePair == -1:
        prePair = pair[1]
    else:
        if prePair > pair[1]:
            return "YES"
        
    # 우선순위 변경해보기
    comb = list(itertools.combinations(arrow, 2))
    for a, b in comb:
        temp = taehyun.copy()
        aIdx = temp.index(a)
        bIdx = temp.index(b)
        temp[aIdx], temp[bIdx] = temp[bIdx], temp[aIdx]
        if temp not in prePriority:
            if solution(priorityList, temp, prePriority, prePair) == "YES":
                return "YES"
    else:
        return "NO"
    
n = input()
inputList = []
for i in range(int(n)):
    priorityList = []
    for j in range(9):
        li = input()
        priorityList.append(list(map(int, li.split())))
    inputList.append(priorityList)

for input_  in inputList:
    print(solution(input_, [6, 7, 8, 9, 10], [], -1))