n,k=map(int,input().split())
result=[]
count=0
for i in range(1,n+1):
    if n %i==0:
        count+=1
        result.append(i)
    
result.sort()

if count<k:
    print(0)
else:
    print(result[k-1])

        