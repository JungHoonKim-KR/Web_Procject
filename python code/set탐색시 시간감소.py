import sys
n=int(input())
A=[]
#탐색할 시 리스트 보다 set을 이용하면 중복없이 o(1)로 탐색가능
#탐색시 시간을 줄이기위해 set을 생각해보자
A=set(map(int,sys.stdin.readline().split()))

m=int(input())
B=list(map(int,sys.stdin.readline().split()))

for i in range(m):
    if B[i] in A:
        print('1')
    else:
        print('0')

