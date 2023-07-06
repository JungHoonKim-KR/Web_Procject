import numpy as np
A=[]
B=[]
result_in=0
f=open('./python code/cramer_in.txt','r')
f1=open('./python code/cramer_out.txt','w')
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
        
A=np.array(A)
B=np.array(B)
result=[]

if(len(A)==2):
    A1=A.copy()
    A2=A.copy()
    
    for i in range(0,len(A)):
        A1[i][0]=B[i]
        A2[i][1]=B[i]
        
    det_A=round(np.linalg.det(A))
    det_A1=round(np.linalg.det(A1))
    det_A2=round(np.linalg.det(A2))
    
    x1=det_A1/det_A
    x2=det_A2/det_A 
    
    result.extend(["x1= ",x1,"x2= ",x2])
    f1.write(str(result))
        
elif (len(A)==3):
    A1=A.copy()
    A2=A.copy()
    A3=A.copy()
    
    for i in range(0,len(A)):
        A1[i][0]=B[i]
        A2[i][1]=B[i]
        A3[i][2]=B[i]
        
    det_A=round(np.linalg.det(A))
    det_A1=round(np.linalg.det(A1))
    det_A2=round(np.linalg.det(A2))
    det_A3=round(np.linalg.det(A3))
        
    x1=det_A1/det_A
    x2=det_A2/det_A
    x3=det_A3/det_A   
    
  
    result.extend(["x1= ",x1,"x2= ",x2,"x3=",x3])
    f1.write(str(result))
    
f.close()
f1.close()