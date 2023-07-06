from collections import deque
class Deque:
    def __init__(self):
        self.items=deque()
    def push_front(self,item):
        self.items.appendleft(item)

    def push_back(self,item):
        self.items.append(item)

    def pop_front(self):
        if len(self.items)==0:
            return -1
        else:
            temp=self.items[0]
            self.items.popleft()
            return temp

    def pop_back(self):
        if len(self.items)==0:
            return -1
        else:
            temp=self.items[len(self.items)-1]
            self.items.pop()
            return temp
    def size(self):
        return len(self.items)

    def empty(self):
        if len(self.items)==0:
            return 1
        else:
            return 0
    
    def front(self):
        if len(self.items)==0:
            return -1
        else:
            return self.items[0]
    def back(self):
        if len(self.items)==0:
            return -1
        else:
            return self.items[len(self.items)-1]
import sys
N=int(input())
s=Deque()
answer=[]
a=[]
for i in range(0,N):
    a=list(sys.stdin.readline().split())

    if a[0]=='push_back':
        s.push_back(int(a[1]))
    elif a[0]=='push_front':
        s.push_front(int(a[1]))
    elif a[0]=='front':
        answer.append(s.front())
    elif a[0]=='back':
        answer.append(s.back())
    elif a[0]=='empty':
        answer.append(s.empty())
    elif a[0]=='pop_front':
        answer.append(s.pop_front())
    elif a[0]=='pop_back':
        answer.append(s.pop_back())
    elif a[0]=='size':
        answer.append(s.size())

for i in range(0,len(answer)):
    print(answer[i])










    
        