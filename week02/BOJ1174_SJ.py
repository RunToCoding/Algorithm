def main():
    # 입력
    n = int(input())
    
    # 자리수 별 조합 개수 확인 -> 10Cr
    posCount = []
    for i in range(1, 6):
        posCount.append(combination_count(10, i))
    posCount.extend(posCount[-2::-1] + [1])       # [10, 45, 120, 210, 252, 210, 120, 45, 10, 1]
    
    # 없는 수일 경우 -1 반환
    if n > sum(posCount): return -1
    
    # 알고리즘에 필요한 변수 선언
    count = 0      # 현재 몇번째인지 변수
    FLAG = False   # 끝내기 위한 Flag 변수
    result = ''
    
    # 알고리즘 설명
    # 10개의 숫자중 n(임의의 숫자)개를 뽑으면 순서는 알아서 정해지므로 개수만 확인하면 구할 수 있다.
    # 조합을 더해가며 입력값 n보다 넘어갈 경우 맨 앞자리를 지정하고 그 다음 자리수를 구하는 방법으로 1의자리까지 반복하면 답이 나옴
    # 알고리즘 수행
    for i, c in enumerate(posCount): # n번째 줄어드는 수가 몇자리 수인지 확인
        count += c
        if count > n:                # n번째 줄어드는 수는 (i+1)자리수
            count -= c
            for start in range(i, -1, -1): # 찾고자 하는 숫자의 맨앞자리부터 찾기(맨앞자리의 가장 작은 숫자는 start)
                for j in range(start, 10): # start ~ 9까지 반복
                    if FLAG: break         # 딱 맞을 때 찾기 끝내기
                    tempCount = combination_count(j, start) # 맨앞자리가 j일때 나올 수 있는 조합의 수
                    count += tempCount
                    if count > n:          # n보다 클때 맨앞자리는 j로 지정 후 그 다음 자리부터 다시 찾기
                        count -= tempCount
                        result += str(j)
                        break
                    elif count == n:       # n과 같을 경우 해당 조합중 가장 큰 조합으로 j부터 (start+1)개를 -1씩하여 출력
                        result += "".join(list(map(str, range(j, j-(start+1), -1))))
                        FLAG = True        # 찾기 끝냄
                        break
            return int(result)
        elif count == n: # i+1자리수중 가장 큰 값 (9부터 i+1개)
            return int("".join(list(map(str, range(9, 9-(i+1),-1)))))
    
def combination_count(n, r):
    # 조합의 개수 구하는 함수 (nCr)
    numerator, denominator = 1, 1
    for j in range(n, n - min(r, n-r), -1): numerator *= j  # 분자 계산
    for j in range(1, min(r, n-r)+1): denominator *= j      # 분모 계산
    return numerator // denominator                         # 조합 개수

main()