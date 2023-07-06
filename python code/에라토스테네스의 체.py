N,K=map(int,input().split())
p=0
m=[]
count=0
for i in range(1,N+1):
    m.append(i)

for i  in range(2,N+1):
    for j in range(i,N+1,i):
        if j in m:
            m.remove(j)
            count+=1
                
            if count==K:
                print(j)     
                        
