n=int(input())

line=0
end=0

while n>end:
    line+=1
    end+=line
    
l=end-n

if line %2==0:
    top=line-l
    bottom=l+1
else:
    top=l+1
    bottom=line-l
    
print("%d/%d"%(top,bottom))
    

            