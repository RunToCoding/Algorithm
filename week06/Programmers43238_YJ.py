def main():
  print(solution(6, [7, 10]))

def solution(n, times):
  left = min(times)
  right = max(times) * n
  
  result = 0
  while left <= right:
    count = 0
    mid = (left + right) // 2
    
    for time in times:
      count += mid // time
      
    if count < n:
      left = mid + 1
    else:
      result = mid
      right = mid - 1
    
  return result

main()