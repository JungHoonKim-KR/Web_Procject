import sys
T=int(input())


def fact(n):
    if n<=1:
        return 1
    else:
        return n*fact(n-1)

for i in range(T):
    n,m=map(int,sys.stdin.readline().split())
    #조합공식 nCr= n!//((n-r)!*r!)
    #서로다른 n개에서 r개를 뽑는다
    a=fact(m)//(fact(n)*fact(m-n))
    print(a)

