N=int(input())

card=[]
card=list(map(int,input().split()))

M=int(input())

card1=[]
card1=list(map(int,input().split()))

count={} #딕셔너리

for card2 in card:
    if card2 in count:
        count[card2]+=1
    else:
        count[card2]=1

for target in card1:
    result=count.get(target)  #찾는 값이 없으면 None을 반환
    if result ==None:
        print(0, end=" ")
    else:
        print(result, end=" ")   #기본값으로 \n이 들어가있지만 개행 제거
        

