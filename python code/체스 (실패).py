N,M=map(int,input().split())

place=[]
for i in range(0,N):
    a=input()
    place.append(a)
count=0
for i in range(0,8):
    for j in range(0,8):
        if place[0][0]=='W':
            if i%2==0:
                if place[i][0]=='W':
                    if j%2==0:
                        if place[i][j]!='W':
                            count+=1
                    elif j%2==1:
                        if place[i][j]!='B':
                            count+=1
            else:
                if place[i][0]=='B':
                    if j%2==0:
                        if place[i][j]!='W':
                            count+=1
                    elif j%2==1:
                        if place[i][j]!='B':
                            count+=1
                else:
                    count+=1

        else:
            if i%2==0:
                if place[i][0]=='B':
                    if j%2==0:
                        if place[i][j]!='B':
                            count+=1
                    elif j%2==1:
                        if place[i][j]!='W':
                            count+=1
            else:
                if place[i][0]=='W':
                    if j%2==0:
                        if place[i][j]!='B':
                            count+=1
                    elif j%2==1:
                        if place[i][j]!='W':
                            count+=1
                else:
                    count+=1


        
print(count)