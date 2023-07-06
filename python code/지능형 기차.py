import sys
_in=0
_out=0
_max=0
_out,_in=map(int,sys.stdin.readline().split())
_max=_in
s=_in

for i in range(3):
    _out,_in=map(int,sys.stdin.readline().split())
    s-=_out
    s+=_in
    
    if (s>_max):
        _max=s

print(_max)
        
        
    
    