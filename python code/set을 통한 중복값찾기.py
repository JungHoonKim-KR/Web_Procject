import sys
n,m=map(int,input().split())

hear=[]
for i in range(n):
    a=sys.stdin.readline().strip()
    hear.append(a)

see=[]
for i in range(m):
    a=sys.stdin.readline().strip()
    see.append(a)

s=set(see)
h=set(hear)

# 리스트에서 중복값을 제거하려면 set하면 바로제거가능
# 중복값을 찾고싶다면 set으로 만들어서 & 교집합연산자쓰면 중복값을 찾을수있음
result=sorted(list(s&h))

print(len(result))
for i in range(len(result)):
    print(result[i])
