# 길이가 1인 모든 단어를 포함하도록 사전을 초기화 
# 사전에서 현재 입력과 일치하는 가장 긴 문자열 w 찾기
# w에 해당하는 사전의 색인 번호 출력, 입력에서 w 제거
# 입력에서 처리되지 않은 다음 글자가 남아 있다면 (c), w+c에 해당하는 단어를 사전에 등록
# 단계 2로 돌아감.

def main():
    print(solution('ABABABABABABABAB'))

def solution(msg):
    dict = {}
    for i in range(26):
        dict[chr(ord('A') + i)] = i + 1     # A부터 Z까지 차례로 사전에 등록
    result = []

    i = 0
    w = msg[i]
    
    while i < len(msg) - 1:
        i += 1 
        w += msg[i]         # w를 한 글자씩 늘리며
        
        if w not in dict:   # 사전에 있는지 확인 -> w + c가 사전에 없으면
            result.append(dict.get(w[:-1])) # w의 인덱스 저장
            dict[w] = len(dict) + 1         # w + c를 사전에 등록
            w = msg[i]                      # w를 c로 재지정
        
    result.append(dict.get(w))  # 마지막 글자를 포함한 문자열의 인덱스 저장
    return result

main()
