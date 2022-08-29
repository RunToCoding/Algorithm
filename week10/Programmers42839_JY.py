# 소수 찾는 함수
def isprime(n):
    if n < 2:
        return False
    # n의 제곱근보다 작은 숫자까지만 나눠봄
    for i in range(2, int(n ** 0.5) + 1):
        #하나라도 나눠떨어진다면 소수가 아님
        if n % i == 0:
            return False
    return True

def solution(n):
    cnt = 0
    for i in range(1,n + 1):
        if isprime(i):
            cnt += 1
    return cnt
