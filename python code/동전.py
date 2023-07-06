N,K=map(int,input().split())
money=[]
import sys
for i in range(N):
    money.append(int(sys.stdin.readline()))
    
coin=0
max=N-1
while(1):  
    if K==0:
        break
    if(money[max]>K):
        max-=1
    else:
        coin+=K//money[max]
        K=K%money[max]
        
        
print(coin)