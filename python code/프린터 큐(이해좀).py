Testcase=int(input())
import sys
from collections import deque
for i in range(Testcase):
    # n=문자개수  #p는 알고싶은 값의 인덱스
    n,p=map(int,sys.stdin.readline().split())
    document=list(map(int,sys.stdin.readline().split()))
    document=deque(document)
    count=0
    s=document[p]

    while True:
        best=max(document)
        front=document.popleft()
        p-=1

        if best==front:
            count+=1
            if p<0:
                print(count)
                break
        else:
            document.append(front)
            if p<0:
                p=len(document)-1
                


      




      




         

        


    
        
    
  

