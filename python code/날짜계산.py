E,S,M=map(int,input().split())
e=m=s=1
count=1
while(1):
    if e==E and s==S and m==M:
        break
    e+=1
    s+=1
    m+=1
    count+=1
    
    if e>15:
        e=1
    if s>28:
        s=1
    if m>19:
        m=1
        
print(count)
        
