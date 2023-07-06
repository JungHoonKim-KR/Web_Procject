result=[]
for i in range(3):
    yut=list(map(int,input().split()))

    if (yut.count(1)==0):
        result.append('D')
    elif (yut.count(1)==1):
         result.append('C')
    elif (yut.count(1)==2):
         result.append('B')
    elif (yut.count(1)==3):
         result.append('A')
    else :
        result.append('E')
        
for i in range(len(result)):
    print(result[i])