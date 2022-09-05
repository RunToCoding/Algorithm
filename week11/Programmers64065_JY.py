def solution(s):
    answer = [] #튜플
    # 입력값 배열로 바꿔서 길이 순서대로 temp에 저장
    s = s [ 2:-2 ]
    s = s.split("},{")
    s.sort(key=len)
    
    for i in s:
        temp = i.split(',')
        for j in temp:
            if int(j) not in answer:
                answer.append(int(j))
    return answer
