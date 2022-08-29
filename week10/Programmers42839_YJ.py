from itertools import permutations
import math

def main():
    numbers = input()
    print(solution(numbers))

def solution(numbers):
    numList = [n for n in numbers]      # numbers의 한 글자씩 numList에 담음
    permutedNumSet = set()

    for i in range(len(numList)):       # 숫자들 중 i개의 수를 골라 순열을 만들고 그 결과를 집합에 저장
        permutedNumSet |= set(map(''.join, (permutations(numList, i + 1))))
    permutedNumSet = set(map(int, permutedNumSet))  # 각 순열을 숫자로 만들어 0으로 시작하는 수들의 중복 제거

    print(permutedNumSet)

    count = 0
    for num in permutedNumSet:
        if isPrime(num):
            count += 1
    
    return count

def isPrime(n):
    if n == 0 or n == 1:
        return False
    else:
        for i in range(2, int(math.sqrt(n)) + 1):
            if n % i == 0:
                return False
        return True

main()