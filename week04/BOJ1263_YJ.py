import sys

def main():
  N = int(input())
  tasks = []
  
  for _ in range(N):
    t, s = map(int, sys.stdin.readline().split())
    tasks.append([s, t])
    
  print(solution(tasks))
  
def solution(tasks):
  tasks.sort(reverse=True)      # 마감 기한이 늦는 순서대로 정렬
  wakeup = tasks[0][0]          # 기상 시각
  
  for deadline, time in tasks:
    wakeup -= time                          # 소요 시간만큼 기상 시각을 앞당김
    
    if deadline - wakeup < time:            # 작업할 수 있는 시간이 소요 시간보다 적으면
      wakeup -= time - (deadline - wakeup)  # 부족한 만큼 기상 시각을 앞당김
    
    if wakeup < 0:  # 하루 안에 끝낼 수 없으면 -1 return
      return -1
    
  return wakeup     # 기상 시각 반환

main()