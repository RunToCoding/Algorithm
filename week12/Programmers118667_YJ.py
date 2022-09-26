from collections import deque

def solution(queue1, queue2):
    queue1, queue2 = deque(queue1), deque(queue2)
    q1Sum, q2Sum = sum(queue1), sum(queue2)

    for i in range(len(queue1) * 4): # 두 큐의 모든 원소를 교환하고 다시 복구
        if q1Sum == q2Sum:
            return i

        if q1Sum > q2Sum:
            num = queue1.popleft()
            queue2.append(num)
            q1Sum -= num
            q2Sum += num
        else:
            num = queue2.popleft()
            queue1.append(num)
            q2Sum -= num
            q1Sum += num

    return -1