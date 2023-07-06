N,K=map(int,input().split())
count=N
list=[]

for i in range(1,N+1):
    list.append(i)          #1~N까지

num=0   # 제거될 사람의 인덱스 번호
answer=[]       #제거된 사람들을 넣을 배열

for t in range(N):
    num+=K-1
    if num >=len(list):
        num=num%len(list)

    
    answer.append(str(list.pop(num)))

print("<",", ".join(answer)[:],">",sep='')
    


