N=[]
_max=0
_id=[1,1]
import sys
for i in range(0,9):
        a=list(map(int,sys.stdin.readline().split()))
        N.append(a)
        s=max(a)
        if _max<s:
            _max=s
            _id=i+1,N[i].index(_max)+1

print(_max)
print(*_id)
        




