# 첫째 줄에 N과 K가 주어진다. N은 10^7보다 작거나 같은 자연수이고, K는 1,000보다 작거나 같은 자연수이다.

# 3 => 111 -> 21
# 4 => 1111 -> 22 -> 4
# => 같은 수끼리의 합 => 2^n

# 3 = 11 => 1이 2개
# 4 = 100 => 1이 1개
# => 이진수로 나타냈을 때 1의 개수 = 2^n끼리의 곱으로 나타낼 수 있는 수의 개수

def main():
  N, K = map(int, input().split())
  print(solution(N, K))
  
def solution(N, K):
  num = N
  while bin(num).count('1') > K:
    num += 1
  
  return (num - N)

main()