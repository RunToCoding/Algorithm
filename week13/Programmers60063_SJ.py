import sys

def solution(board):
    # 초기 설정
    minSeconds = sys.maxsize # 최대 정수로 설정
    startPos = [[1, 1], [1, 2]] # 시작위치
    destination = [len(board), len(board)] # 목적지
    visited = {} # 방문한 위치 저장 (key: 방문위치(Sting type), value: 최소시간(int type)
    visited[str(startPos)] = 0 # 시작지점 설정
    q = [[startPos, 0]] # 방문할 q 설정 [[위치, 소요시간], ...]
    
    while q:
        # 현재 위치
        curPos, seconds = q.pop(0)
        
        # 목적지에 도착한 경우 처리
        if destination in curPos:
            if minSeconds > seconds: # 최소시간인 경우 minSeconds 갱신
                minSeconds = seconds
            continue
        
        # 방문 기록보다 느린경우 이후에 재방문 예정이므로 패스
        if visited[str(curPos)] < seconds:
            continue
        
        # 이동 가능한 경로 확인
        possibleNextPostion = get_next_position(curPos, board)

        for pos in possibleNextPostion:
            # 새로운 위치인 경우
            if str(pos) not in visited.keys():
                visited[str(pos)] = seconds + 1 # 방문 목록에 추가
                q.append([pos, seconds + 1]) # 다음 이동할 큐에 추가
            # 이전에 방문한 위치인 경우
            else:
                # 이전 방문보다 빠른 경우
                if visited[str(pos)] > seconds + 1:
                    visited[str(pos)] = seconds + 1 # 방문 목록 시간 갱신
                    q.append([pos, seconds + 1]) # 다음 이동할 큐에 추가
                    
    return minSeconds
        
def get_next_position(curPos, board):
    # 초기 변수 설정
    [y1, x1], [y2, x2] = curPos # 현재 위치 값
    robotDirect = check_robot_direction(curPos) # 가로/세로
    possibleNextPostion = []
    
    # 상
    nextPos = set_robot_position([[y1-1, x1], [y2-1, x2]])
    if check_move_possible(curPos, nextPos, board, 1):
        possibleNextPostion.append(nextPos)
    # 하
    nextPos = set_robot_position([[y1+1, x1], [y2+1, x2]])
    if check_move_possible(curPos, nextPos, board, 2):
        possibleNextPostion.append(nextPos)
    # 좌
    nextPos = set_robot_position([[y1, x1-1], [y2, x2-1]])
    if check_move_possible(curPos, nextPos, board, 3):
        possibleNextPostion.append(nextPos)
    # 우
    nextPos = set_robot_position([[y1, x1+1], [y2, x2+1]])
    if check_move_possible(curPos, nextPos, board, 4):
        possibleNextPostion.append(nextPos)
    # 회전(시계)
    if robotDirect == 1: # 가로
        nextPos = set_robot_position([[y1-1, x1+1], [y2, x2]]) # 우측 날개 기준
        if check_move_possible(curPos, nextPos, board, 5):
            possibleNextPostion.append(nextPos)
        nextPos = set_robot_position([[y1, x1], [y2+1, x2-1]]) # 좌측 날개 기준
        if check_move_possible(curPos, nextPos, board, 5):
            possibleNextPostion.append(nextPos)
    elif robotDirect == -1: # 세로
        nextPos = set_robot_position([[y1, x1], [y2-1, x2-1]]) # 위 날개 기준
        if check_move_possible(curPos, nextPos, board, 5):
            possibleNextPostion.append(nextPos)
        nextPos = set_robot_position([[y1+1, x1+1], [y2, x2]]) # 아래 날개 기준
        if check_move_possible(curPos, nextPos, board, 5):
            possibleNextPostion.append(nextPos)
    # 회전(반시계)
    if robotDirect == 1: # 가로
        nextPos = set_robot_position([[y1+1, x1+1], [y2, x2]]) # 우측 날개 기준
        if check_move_possible(curPos, nextPos, board, 6):
            possibleNextPostion.append(nextPos)
        nextPos = set_robot_position([[y1, x1], [y2-1, x2-1]]) # 좌측 날개 기준
        if check_move_possible(curPos, nextPos, board, 6):
            possibleNextPostion.append(nextPos)
    elif robotDirect == -1: # 세로
        nextPos = set_robot_position([[y1, x1], [y2-1, x2+1]]) # 위 날개 기준
        if check_move_possible(curPos, nextPos, board, 6):
            possibleNextPostion.append(nextPos)
        nextPos = set_robot_position([[y1+1, x1-1], [y2, x2]]) # 아래 날개 기준
        if check_move_possible(curPos, nextPos, board, 6):
            possibleNextPostion.append(nextPos)
            
    return possibleNextPostion

def check_move_possible(curPos, nextPos, board, moveType):
    # 변수 설정
    robotDirect = check_robot_direction(curPos) # 가로 : 1, 세로 : -1
    [y1, x1], [y2, x2] = nextPos
    
    # 상
    if moveType == 1: # 가로세로 상관 없음(위 날개 기준)
        if y1 > 0 and board[y1-1][x1-1] == 0 and board[y2-1][x2-1] == 0:
            return True
    # 하
    elif moveType == 2: # 가로세로 상관 없음(아래 날개 기준)
        if y2 <= len(board) and board[y1-1][x1-1] == 0 and board[y2-1][x2-1] == 0:
            return True
    # 좌
    elif moveType == 3: # 가로세로 상관 없음(왼쪽 날개 기준)
        if x1 > 0 and board[y1-1][x1-1] == 0 and board[y2-1][x2-1] == 0:
            return True
    # 우
    elif moveType == 4: # 가로세로 상관 없음(오른쪽 날개 기준)
        if x2 <= len(board[0]) and board[y1-1][x1-1] == 0 and board[y2-1][x2-1] == 0:
            return True
    # 회전(시계)
    elif moveType == 5:
        if robotDirect == 1: # 가로
            if curPos[1] == nextPos[1] and y1 > 0 and board[y1-1][x1-1] == 0 and board[y1-1][x1-2] == 0: # 우측 날개 기준 회전
                return True
            elif curPos[0] == nextPos[0] and y2 <= len(board) and board[y2-1][x2-1] == 0 and board[y2-1][x2] == 0: # 좌측 날개 기준 회전
                return True
        elif robotDirect == -1: # 세로
            if curPos[0] == nextPos[1] and x1 > 0 and board[y1-1][x1-1] == 0 and board[y1][x1-1] == 0: # 위 날개 기준 회전
                return True
            elif curPos[1] == nextPos[0] and x2 <= len(board[0]) and board[y2-1][x2-1] == 0 and board[y2-2][x2-1] == 0: # 아래 날개 기준 회전
                return True
    # 회전(반시계)
    elif moveType == 6:
        if robotDirect == 1: # 가로
            if curPos[1] == nextPos[0] and y2 <= len(board) and board[y2-1][x2-1] == 0 and board[y2-1][x2-2] == 0: # 우측 날개 기준 회전
                return True
            elif curPos[0] == nextPos[1] and y1 > 0 and board[y1-1][x1-1] == 0 and board[y1-1][x1] == 0: # 좌측 날개 기준 회전
                return True
        elif robotDirect == -1: # 세로
            if curPos[0] == nextPos[0] and x2 <= len(board[0]) and board[y2-1][x2-1] == 0 and board[y2][x2-1] == 0: # 위 날개 기준 회전
                return True
            elif curPos[1] == nextPos[1] and x1 > 0 and board[y1-1][x1-1] == 0 and board[y1-2][x1-1] == 0: # 아래 날개 기준 회전
                return True
    
    return False

def set_robot_position(pos):
    direction = check_robot_direction(pos)
    
    if direction == 1: # 가로
        return sorted(pos, key=lambda x:x[1])
    elif direction == -1: # 세로
        return sorted(pos, key=lambda x:x[0])

def check_robot_direction(curPos):
    [y1, x1], [y2, x2] = curPos
    
    # 세로
    if abs(x1 - x2) == 0:
        return -1
    # 가로
    if abs(y1 - y2) == 0:
        return 1

solution([[0, 0, 0, 1, 1],[0, 0, 0, 1, 0],[0, 1, 0, 1, 1],[1, 1, 0, 0, 1],[0, 0, 0, 0, 0]])
# solution([[0, 0, 0, 0],[0, 0, 0, 0],[0, 0, 0, 0],[0, 0, 0, 0]])