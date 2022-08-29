dx = [-1, 0, 1, 0]
dy = [0, -1, 0, 1]

def solution(x, y, count): 
    global answer
    #현재 방문 위치 visited에 추가
    visited[x][y] = 1
    #집에 도착한 경우
    if [x,y] == [0, C-1] :
        if count == K:
            answer += 1
        return
    #상하좌우 모든 방향
    for i in range(4):
        nx = x + dx[i]
        ny = y + dy[i]
        #이동할 곳이 T가 아니고, 방문했던 적이 없고, maps 범위 안에 있다면
        if 0 <= nx < R and 0 <= ny < C and maps[nx][ny] != 'T' and visited[nx][ny] == 0:
            visited[nx][ny] = 1 #방문 표시
            solution(nx, ny, count + 1) # 이동할 위치, count + 1를 재귀로 다시 호출해줌
            visited[nx][ny] = 0 #다시 되돌아온 경우 visited를 0으로 해준다.

R, C, K = map(int, input().split())
maps = [list(input()) for _ in range(R)]
visited = [[0 for _ in range(C)] for _ in range (R)]
answer = 0
solution(R-1, 0, 1)
print(answer)

