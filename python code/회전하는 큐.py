from collections import deque

        
N,M=map(int,input().split())
order=list(map(int,input().split()))

arr=deque()

for i in range(1,N+1):
    arr.append(i)

count=0
idx=0
for i in range(len(order)):
    idx=order[i]
    
    while True:
        if arr[0]==idx:
            arr.popleft()
            break
        else:
            if arr.index(idx) < len(arr)/2:
                while arr[0]!=idx:
                    arr.append(arr.popleft())
                    count+=1
            else:
                while arr[0]!=idx:
                    arr.appendleft(arr.pop())
                    count+=1
print(count)     
            

        