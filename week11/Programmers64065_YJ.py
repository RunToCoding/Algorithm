def main():
    s = "{{1,2,3},{2,1},{1,2,4,3},{2}}"
    print(solution(s))

def solution(s):    # s = "{{2},{2,1},{2,1,3},{2,1,3,4}}"
    answer = []
    arr = s[2:-2].split('},{')
    arr.sort(key = lambda x : len(x))  # 배열의 각 원소의 길이를 기준으로 정렬
    for i in arr:  # for i in ['2', '2,1', '2,1,3', '2,1,3,4']
        for j in i.split(','):
            if int(j) not in answer:
                answer.append(int(j))
    return answer

main()