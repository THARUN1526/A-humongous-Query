for aa in range(int(input())):
    s=input()
    n=int(input())
    l=len(s)
    i=2**(l-1)
    mmm=100000000
    cnt=0
    while i<2**l:
        b=bin(i)
        b=b[2:]
        #print(b)
        if b[0]=='1' and b[len(b)-1]=='0':
            x=0
            y=0
            for j in range(len(b)):
                if b[j]=='1':
                    x=x+y+1
                else:
                    y+=x
            if y==n:
                cnt=0
                for j in range(len(b)):
                    if s[j]!=b[j]:
                        cnt+=1
                #print(cnt)        
                mmm=min(cnt,mmm)        
        i+=1
    if mmm==100000000:
        print("NO")
    else:
        print("YES")
        print(mmm)      