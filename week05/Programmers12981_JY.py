# 영어 끝말잇기

def solution(n, words):
    answer = [0 , 1] #번호, 차례
    wordSet = set({words[0]})
    curChar = words[0][-1]
    stop = True
    len_ = len(words)
    for idx in range(1, len_):

        if idx%n == 0: answer[1] += 1
        w = words[idx]
        if curChar == w[0] and w not in wordSet:
            wordSet.add(w)
            curChar = w[-1]
        else:
            answer[0] = idx%n+1
            break
    return answer if answer[0] != 0 else [0, 0]

print(solution(2, ["hello", "one", "even", "never", "now", "world", "draw"]))