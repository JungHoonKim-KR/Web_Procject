def S (N,A,B):
   
    sum=0
    count=0
    while (count!=N):
        B_max=max(B)
        B_max_idx=B.index(B_max)
        A_min=min(A)
        A_min_idx=A.index(A_min)
        A.insert(0,A_min)
        B.insert(0,B_max)
      
        del B[B_max_idx+1]
        del A[A_min_idx+1]
        sum+=A[0]*B[0]
        del A[0]
        del B[0]
        
        count+=1
    return sum
        
N=int(input())

A=[]
B=[]

A=list(map(int,input().split()))
B=list(map(int,input().split()))

print(S(N,A,B))