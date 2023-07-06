import copy
import numpy as np

A=[]
B=[]
result_in=0
f=open('./python code/LU_in.txt','r')
f1=open('./python code/LU_out.txt','w')

c=0
while True:
    line=f.readline()
    if not line: 
        break
    if line=='\n':
        result_in+=1
        continue
    if(result_in==0):
        A.append(list(map(int,line.split())))
      
    elif(result_in==1):
        B.append(int(line.strip('\n')))
        c+=1

L=[]
U=[]
LU=[]

for i in range(len(A)):
    temp=[]
    for j in range(len(A)):
        temp.append(0) 
    L.append(temp)
    
for i in range(len(A)):
    temp=[]
    for j in range(len(A)):
        temp.append(0) 
    U.append(temp)

for i in range(len(A)):
    temp=[]
    for j in range(len(A)):
        temp.append(0) 
    LU.append(temp)
    
for i in range(len(A)):
    for j in range(len(A)):
        if (i==j):
            L[i][j]=1
        elif (i!=j and j<i):
            L[i][j]=0

for i in range(len(A)):
    for j in range(i,len(A)):
        if(i<=j):
            U[i][j]=0
        
            
for i in range(len(A)):
    for j in range(0,len(A)):
        temp=0
        
        if(i==0):
            U[i][j]=A[i][j]
            continue
                
        if(i>j and j==0):
            L[i][j]=A[i][j]/U[0][j]
            
            continue
        
        if(i<=j):
            for k in range(len(A)): 
                temp+=L[i][k]*U[k][j]
            U[i][j]=A[i][j]-temp
            
        else :
            for k in range(len(A)): 
                if(k!=len(A)-1):
                    temp+=L[i][k]*U[k][j]
                else:
                    L[i][j]=(A[i][j]-temp)/U[j][j]


Y=[0 for i in range(len(L))]
Y[0]=B[0]/L[0][0]


for i in range(1,len(Y)):
    y1=0
    y=L[i][i]
    for j in range(len(Y)):
        y1+=L[i][j]*Y[j]
    Y[i]=(B[i]-y1)*(1/y)
    


X=[0 for i in range(len(L))]
X[len(Y)-1]=Y[len(Y)-1]/(U[len(Y)-1][len(Y)-1])


for i in range(len(U)-2,-1,-1):
    x1=0
    x=U[i][i]
    for j in range(len(X)-1,i,-1):
        x1+=U[i][j]*X[j]
    X[i]=(Y[i]-x1)*(1/x)

r=""
for i in range(1,len(X)+1):
    if i==len(X):
        r+="x%d : "%(i)+str(X[i-1])+"   "
        break
    r+="x%d : "%(i)+str(X[i-1])+",   "
    
   
s=""

f1.write(str(np.array(U)))
f1.write("\n \n")
f1.write(str(np.array(L)))
f1.write("\n \n")
f1.write("Y ")
f1.write(str(np.array(Y)))
f1.write("\n \n")
f1.write(str(np.array(r)))

f.close()
f1.close()