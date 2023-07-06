import sys
n=int(input())

rope=[]
for i in range(n):
    a=int(sys.stdin.readline())
    rope.append(a)

#rope.reverse() 뭔차인지
rope.sort(reverse=True)
result=[]

for i in range(0,n):
    result.append(rope[i]*(i+1))

print(max(result))
