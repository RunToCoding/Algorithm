# 수찾기 - 이진탐색?

N = int(input())
nums = list(map(int, input().split()))
nums.sort()

M = int(input())
find = list(map(int, input().split()))

for n in find:
    check = False
    start, end = 0, N
    while start <= end:
        mid = (start+end)//2
        if mid < 0 or mid >= N : break
        if nums[mid] > n: end = mid-1
        elif nums[mid] < n : start = mid+1
        else:
            check = True
            break
    
    if check : print(1)
    else: print(0)

