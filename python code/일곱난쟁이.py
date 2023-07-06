import sys

A=[]
total=0
for i in range(0,9):
    a=int(sys.stdin.readline())
    total+=a
    A.append(a)


for i in range(9):
    for j in range(i+1,9):
        if total-(A[i]+A[j]) ==100:
            one,two=A[i],A[j]
            break

A.remove(one)
A.remove(two)
A.sort()
for i in range(0,len(A)):
    print(A[i])




        




    

    
    