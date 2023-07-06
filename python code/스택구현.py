class stack:
    def __init__(self): #스택 객체 생성
        self.items=[]

    def push(self,item):
        self.items.append(item)
    
    def pop(self):
        if len(self.items)==0:
            return -1
        else:
            return self.items.pop()
    
    def size(self):
        return len(self.items)

    def isEmpty(self):
        if len(self.items)==0:
            return 1
        else:
            return 0
    
    def top(self):
        if len(self.items)==0:
            return -1
        else:
            return self.items[-1]
    
import sys
N=int(sys.stdin.readline())
s=stack()
a=[]
for i in range(0,N):
    n=list(sys.stdin.readline().split())
    if n[0]=='push':
        s.push(int(n[1]))
    elif n[0]=='top':
        a.append(s.top())
    elif n[0]=='size':
        a.append(s.size())
    elif n[0]=='empty':
        a.append(s.isEmpty())
    elif n[0]=='pop':
        a.append(s.pop())

for i in range(0,len(a)):
    print(a[i])








