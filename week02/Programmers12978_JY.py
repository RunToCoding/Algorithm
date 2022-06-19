from collections import deque

def solution(N, road, K):
    answer = set()
    roadMap = [[0 for i in range(N+1)] for j in range(N+1)]
    
    queue = deque([])
    queueCheck = [1e9]*(N+1)

    roadPosible = {}

    for a, b, c in road:
        if not roadMap[a][b] or roadMap[a][b] > c:
            roadMap[a][b] = c
            roadMap[b][a] = c

        if a not in roadPosible.keys(): roadPosible[a] = {b}
        else: roadPosible[a].add(b)
        if b not in roadPosible.keys(): roadPosible[b] = {a}
        else: roadPosible[b].add(a)

    queue.append([1, 0]) # 현재마을, 지금까지온 거리
    queueCheck[1] = 0

    while queue:
        Curnum, dis = queue.popleft()
        if queueCheck[Curnum] < dis: continue
        
        for nextNum in roadPosible[Curnum]:
            nextDis = dis+roadMap[Curnum][nextNum]

            if nextDis <= K and nextDis < queueCheck[nextNum]:
                answer.add(nextNum)
                queueCheck[nextNum] = nextDis
                queue.append([nextNum, nextDis])

    return len(answer)+1 if answer else 0


print(solution(6, [[1,2,1],[1,3,2],[2,3,2],[3,4,3],[3,5,2],[3,5,3],[5,6,1]], 4))