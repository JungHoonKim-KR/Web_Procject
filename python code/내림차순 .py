n=list(input())

#문자열리스트를 숫자리스트로 변환 후 다시 숫자리스트로
n2=list(map(str,sorted(list(map(int,n)),reverse=True)))

#문자열 리스트를 문자열로
print(''.join(n2))