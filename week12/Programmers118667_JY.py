from collections import deque

def solution(queue1, queue2):
    count, answer = 0, -1
    que1, que2 = deque(queue1), deque(queue2)
    sumQue1, sumQue2 = sum(que1), sum(que2)
    maxCount =  len(queue1) * 3
    
    while ( (que1 and que2) and count != maxCount):
        # Queue1과 Queue2의 합이 같을 때
        if ( sumQue1 == sumQue2 ):
            answer = count
            return answer
        # Queu1의 합이 더 클 때
        elif ( sumQue1 < sumQue2 ):
            tempEle = que2.popleft()
            que1.append(tempEle)
            sumQue1 += tempEle
            sumQue2 -= tempEle
        # Queu2의 합이 더 클 때
        else :
            tempEle = que1.popleft()
            que2.append(tempEle)
            sumQue1 -= tempEle
            sumQue2 += tempEle
        count += 1
    return -1
