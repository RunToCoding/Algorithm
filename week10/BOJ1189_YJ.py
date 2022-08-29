def main():
    # rows, cols, goalDistance = map(int, input().split())
    # table = [list(input()) for _ in range(rows)]
    rows, cols, goalDistance = 3, 4, 6
    table = [['.', '.', '.', '.'], ['.', 'T', '.', '.'], ['.', '.', '.', '.']]
    print(solution(table, rows, cols, goalDistance))

def solution(table, rows, cols, goalDistance):
    r = rows - 1; c = 0
    table[r][c] = 'T'   # 시작 지점 방문 처리

    roads = []
    dfs(table, r, c, 1, roads)

    count = 0
    for road in roads:
        if road == goalDistance:
            count += 1
        
    return count

def dfs(table, r, c, distance, roads):
    rows, cols = len(table), len(table[0])

    if r == 0 and c == cols - 1:
        print('distance: ' + str(distance))
        roads.append(distance)
        
    dr = [0, -1, 0, 1]; dc = [1, 0, -1, 0]
    table[r][c] = 'T'

    for i in range(4):
        nr, nc = r + dr[i], c + dc[i]
        if (0 <= nr < rows and 0 <= nc < cols and table[nr][nc] != 'T'):
            dfs(table, nr, nc, distance + 1, roads)

main()