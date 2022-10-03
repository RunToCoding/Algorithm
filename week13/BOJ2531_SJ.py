# 입력
## (접시수, 스시종류개수, 연속접시개수, 쿠폰번호)
plateCnt, sushiKindCnt, continuePossibleCnt, cuponNo = list(map(int, input().split()))
## 스시 놓여있는 테이블 상태
table = []
for _ in range(plateCnt):
    table.append(int(input()))

# 최대 종류 (정답)
maxCnt = 0

for i in range(len(table)):
    # 연속된 스시 선택(원형 리스트 처리)
    ## 기존 리스트가 끊기지 않는 경우
    if i <= len(table) - continuePossibleCnt: 
        continueSushi = table[i:i+continuePossibleCnt]
    ## 기존 리스트가 끊기는 경우
    else:
        continueSushi = table[i:]
        continueSushi.extend(table[:continuePossibleCnt - len(table[i:])])
        
    # 쿠폰 포함 or 제외한 스시 종류 개수 구하기
    if cuponNo not in continueSushi: # 쿠폰 스시가 없는경우
        temp_maxCnt = len(set(continueSushi)) + 1
    else: # 쿠폰 스시가 있는 경우
        temp_maxCnt = len(set(continueSushi))
        
    # 먹을 수 있는 최대 스시 개수 처리
    if maxCnt < temp_maxCnt:
        maxCnt = temp_maxCnt
        
print(maxCnt)