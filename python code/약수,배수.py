import sys
result=[]
while (1):
    a,b=map(int,sys.stdin.readline().split())
    if a==0 and b==0:
        break
    else:
        if a%b == 0:
            c='multiple'
            result.append(c)
        elif b%a == 0:
            c='factor'
            result.append(c)
        else :
            c='neither'
            result.append(c)
            
for i in range(len(result)):
    print(result[i])

        
                