import sys

def fib(N):
    zeros=[1,0,1] #fib(0)일때 0 1번 ,fib(1)일떄 0번 fib(2)일때 1번
    ones=[0,1,1] #fib(0)일때 0번 , fib(1)일때 1번,fib(2)일때 1번

    #0과 1이 몇번 불리는지도 피보나치수열로됨
    if N>=3:
        for i in range(2,N):
            zeros.append(zeros[i-1]+zeros[i])
            ones.append(ones[i-1]+ones[i])
    print(f"{zeros[N]} {ones[N]}")

T=int(sys.stdin.readline())

for i in range(T):
    N=int(input())
    fib(N)
