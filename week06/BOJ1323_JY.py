# 숫자 연결하기

N, K = map(int, input().split())
n = N
len_N = (10**len(str(N))) % K
cnt = 1

N, K = int(N), int(K)
mod = N % K
check = [0]*(100001) # 나머지 다 쓴지 확인용
check[mod] = 1
while True:
    if mod == 0: 
        print(cnt)
        break
    else:
        cnt += 1
        mod = (mod*len_N + n) % K
        if check[mod] == 1: 
            print(-1)
            break
        else:
            check[mod] = 1