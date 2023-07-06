import sys

N=int(sys.stdin.readline().rstrip())

num=[]

for i in range(0,N):
    s=int(sys.stdin.readline())
    num.append(s)

def merge_sort(list):
    if len(list)<=1:
        return list
    mid=len(list)//2
    leftList=list[:mid]
    rightList=list[mid:]
    leftList=merge_sort(leftList)
    rightList=merge_sort(rightList)
    return merge(leftList,rightList)


def merge(left,right):
    merged_list=[]
    l=h=0
    while l<len(left) and h<len(right):
        if(left[l]<right[h]):
            merged_list.append(left[l])
            l+=1
        else:
            merged_list.append(right[h])
            h+=1
    merged_list+=left[l:]
    merged_list+=right[h:]
    return merged_list

sorted_list=[]
sorted_list=merge_sort(num)
for i in range(len(sorted_list)):
    print(sorted_list[i])



