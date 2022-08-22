import sys

def main():
  c, n = map(int, sys.stdin.readline().split())
  costs = []

  for _ in range(n):
    costs.append(list(map(int, sys.stdin.readline().split())))
    # costs[0] 원으로 costs[1] 명의 고객을 얻을 수 있음

  print(solution(c, costs))

def solution(c, costs): # 적어도 c명의 고객을 늘려야 함
    minCost = [10000 for _ in range(c + 1)]
    for needCus in range(0, c + 1): # 필요한 고객의 수 needCus를 0부터 c까지 늘려가며 반복
        for cityCost, cityCus in costs:
            cost = cityCost if needCus <= cityCus else minCost[needCus - cityCus] + cityCost
            minCost[needCus] = min(minCost[needCus], cost)

    return minCost[-1]

main()