import sys

a=0
b=0
print("put 2 numbers to plus")

a1,b1=map(float ,sys.stdin.readline().split())

print(a1,"+",b1,"=",a1+b1)
print(a1,"-",b1,"=",a1-b1)
print(a1,"*",b1,"=",a1*b1)
print(a1,"/",b1,"=",a1/b1)