x,y=map(int,input().split())

count=1
days=['SUN','MON','TUE','WED','THU','FRI','SAT']

end=0
for i in range(1,x):
    if i==1 or i==3 or i==5 or i==7 or i==8 or i==10 or i==12:
        end+=31
    elif i==4 or i==6 or i==9 or i==11:
        end+=30
    else:
        end+=28

print(days[(end+y)%7])








    