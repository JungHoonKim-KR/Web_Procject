import sys
N,M=map(int,input().split())
A=[]
B=[]
for i in range(0,N):
    A.append(list(map(int,sys.stdin.readline().split())))
for i in range(0,N):
    B.append(list(map(int,sys.stdin.readline().split())))

C=[]
for i in range(0,N):
    C.append([0]*M)

for i in range(0,N):
    for j in range(0,M):
        C[i][j]=A[i][j]+B[i][j]
        
for i in range(0,N):
    print(*C[i])

    
        






