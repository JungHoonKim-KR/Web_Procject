M=int(input())
N=int(input())

sum=0
_min=[]
count=0

for i in range(M,N+1):
    m=0
    if i==1:
        continue
    
    for j in range(2,int(i**0.5)+1):
        if i%j==0:
            m+=1
            break
        
    if m==0:
        sum+=i
        count+=1
        _min.append(i)
    
if count==0:
    print(-1)
else:
    print(sum)
    print(min(_min))