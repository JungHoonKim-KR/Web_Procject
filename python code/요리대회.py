applicant=[]
c=0
_max=0
sum=0
s=0

for i in range(5):
    a=list(map(int,input().split()))
    applicant.append(a)
    for j in range(len(a)):
        s+=a[j]
    if _max<s:
        c=i
        _max=s
    s=0
    

print(c+1,_max)
    
