import sys
N=int(sys.stdin.readline())

card=[]

from collections import deque

for i in range(1,N+1):
    card.append(i)

dq=deque(card)

while len(dq)!=1:
    dq.append(dq[1])
    dq.popleft()
    dq.popleft()

print(dq[0])