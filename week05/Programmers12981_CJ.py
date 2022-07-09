def solution(n, words):
  #초기값 세팅
  answer = [0,0]
  temp = [] #이미 나온 단어는 temp에 저장
  temp.append(words[0])
  
  for i in range(1, len(words)):#i가 1부터 words 길이까지 반복
    #현재 단어의 마지막 문자가 다음 단어의 첫번째와 같고, temp에 없다면(통과)
    if list(words[i-1])[-1] == list(words[i])[0] and words[i] not in temp:
      #temp에 저장
      temp.append(words[i])
      else:#오답
        #탈락자 번호, 탈락한 라운드 번호 answer에 저장
        answer = [i%n+1, i//n+1]
        break

return answer
