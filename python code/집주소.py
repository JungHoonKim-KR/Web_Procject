import sys

n=[1]
result=[]
while True:
    n=list(sys.stdin.readline().strip())
    if n[0]=='0':
        break
    count=0
    for i in range(len(n)):
        if n[i]=='1':
            count+=2
        elif n[i]=='0':
            count+=4
        else:
            count+=3
            
    count+=len(n)+1
        
    result.append(count)

for i in range(len(result)):
    print(result[i])
    
    


