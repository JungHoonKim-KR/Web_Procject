import sys
from collections import deque
class Queue:
    def __init__(self):
        self.items=deque()

    def push(self,item):
        self.items.append(item)

    def pop(self):
        if len(self.items)==0:
            return -1
        else:
            a=self.items[0]
            self.items.popleft()
            return a
            
    
    def size(self):
        return len(self.items)
        
    def front(self):
        if len(self.items)==0:
            return -1
        else:
            return self.items[0]
    def back(self):
        if len(self.items)==0:
            return -1
        else:
            return self.items[-1]
    def empty(self):
        if len(self.items)==0:
            return 1
        else:
            return 0

N=int(input())
a=[]
s=Queue()
answer=[]
for i in range(0,N):
    a=list(sys.stdin.readline().split())
   
    if a[0]=='push':
        s.push(int(a[1]))
    elif a[0]=='front':
        answer.append(s.front())
    elif a[0]=='back':
        answer.append(s.back())
    elif a[0]=='size':
        answer.append(s.size())
    elif a[0]=='empty':
        answer.append(s.empty())
    elif a[0]=='pop':
        answer.append(s.pop())

for i in range(0,len(answer)):
    print(answer[i])







