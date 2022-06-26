def main():
    land = [[1, 2, 3, 5], [5, 6, 7, 8], [4, 3, 2, 1]]
    print(solution(land))


def solution(land):
    rows = len(land)    # 행의 개수
    if rows == 1:       # 행이 1개이면 그 행의 최댓값 반환
        return max(land[0])
    else:               # 행이 2개 이상인 경우
        for r in range(rows - 1):           # 행을 차례로 내려가면서 다음 행에 현재 행의 최댓값을 더함
            for i in range(4):              # 열 차례로 반복
                temp = land[r][i]           # 현재 열의 값 임시 저장
                land[r][i] = 0              # 현재 열의 값 0 으로 설정
                maxVal = max(land[r])       # 현재 행의 최댓값 찾기
                land[r][i] = temp           # 원래 값으로 복구
                land[r + 1][i] += maxVal    # 다음 행에 현재 행의 최댓값 더함

    return (max(land[-1]))  # 마지막 행의 최댓값 반환


main()
