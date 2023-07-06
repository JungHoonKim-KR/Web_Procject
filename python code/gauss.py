import copy
import numpy as np
import math

A=[]
B=[]
result_in=0
f=open('./python code/gauss_in.txt','r')
f1=open('./python code/gauss_out.txt','w')

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
        A[c].append(int(line.strip('\n')))
        c+=1
        

for j in range(0,len(A)-1):
    temp_list=[]
    for k in range(j):
        temp_list.append(A[0])
        A.pop(0)
    A=sorted(A,key=lambda A :abs(A[j]),reverse=True)
    for k in range(len(temp_list)-1,-1,-1):
        A.insert(0,temp_list[k])
    for i in range(j+1,len(A)):
        temp=-(A[i][j]/A[j][j])
        t=copy.deepcopy(A[j])
        for k in range(len(A)+1):
            t[k]*=temp
        for k in range(len(A)+1):
            A[i][k]+=t[k]      

B=[]
for i in range(len(A)):
    B.append(A[i][len(A)])
    A[i].pop()
    

X=[0 for i in range(len(A))]
X[len(A)-1]=B[len(A)-1]/(A[len(A)-1][len(A)-1])

for i in range(len(A)-2,-1,-1):
    x1=0
    x=A[i][i]
    for j in range(len(X)-1,i,-1):
        x1+=A[i][j]*X[j]
   
    X[i]=(B[i]-x1)*(1/x)


r=""
for i in range(1,len(X)+1):
    if i==len(X):
        r+="x%d : "%(i)+str(X[i-1])+"   "
        break
    r+="x%d : "%(i)+str(X[i-1])+",   "
    
   
f1.write(str(np.array(A)))
f1.write("\n \n")
f1.write(r)

f.close()
f1.close()