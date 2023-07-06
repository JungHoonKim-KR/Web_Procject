import sys
n=int(input())
result=[]

group_word=0
for i in range(n):
    word=sys.stdin.readline()
    
    error=0
    for i in range(len(word)-1):
        if word[i]!=word[i+1]:
            new_word=word[i+1:]
            if word[i] in new_word:
                error+=1
        
    if error==0:
        group_word+=1
    
print(group_word)
    
    
    
            
        
        
            
        
        
            
    
    
    