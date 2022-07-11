def main():
  N = int(input())
  A = set(map(int, input().split(' ')))
  M = int(input())
  B = list(map(int, input().split(' ')))
  
  return solution(A, B)
  
def solution(A, B):
  for n in B:
    if n in A:  print("1")
    else:       print("0")
      
main()