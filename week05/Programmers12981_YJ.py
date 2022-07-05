def main():
  print(solution(2,	["hello", "one", "even", "never", "now", "world", "draw"]))

def solution(n, words):
  for i, word in enumerate(words):
    if (word in words[:i]) or (i > 0 and word[0] != words[i - 1][-1]):  # 이전까지 중 있는 단어이거나 이전 단어의 마지막 글자와 현재 단어의 첫 글자가 다를 때
      return [i % n + 1, i // n + 1]  # 탈락자는 i % n + 1 (=> n=3일 때 1, 2, 3, 1, 2, 3, ...), 차례는 i // n + 1 (=> n=3일 때 1, 1, 1, 2, 2, 2, ...)
  return [0,0]  # 탈락자가 없는 경우

main()
