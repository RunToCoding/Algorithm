MAX = 99999999999

def solution(N, road, K):
    # 변수 설정
    min_distance = [MAX] * N    # 최소 거리
    q = []                  # 방문 예정
    result = 0
    
    # 다익스트라 알고리즘(dijkstra)
    q.append(1)             # 시작 노드
    min_distance[0] = 0
    
    while q:
        curNode = q.pop(0)
        # 방문하지 않은 노드인지 확인
        for s, e, d in road:    # 모든 길 확인
            if curNode in [s, e]:
                # 현재 노드를 s로 만들기
                if curNode == e:
                    s, e = e, s

                # 최소거리 계산 및 다음 노드 확인
                curDistance = min_distance[s-1] + d
                if curDistance < min_distance[e-1]:
                    min_distance[e-1] = curDistance
                    q.append(e)
                    
    # 최종 배달 가능 노드 구하기                    
    for d in min_distance:
        if d <= K:
            result += 1
            
    return result