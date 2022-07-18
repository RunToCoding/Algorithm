import sys

def main():
  N, K = sys.stdin.readline().split()
  print(solution(N, K))

def solution(N, K):
  count = 0
  
  remainder = int(N) % int(K)
  remainders = set()
  
  while 1:
    count += 1
    
    if remainder == 0:
      break
    elif remainder in remainders:
      count = -1
      break
    
    remainders.add(remainder)
    num = str(remainder) + N
    remainder = int(num) % int(K)
    
  return count

main()