
N,M,R=map(int,input().split())

com=[list(map(int,input().split()))for _ in range(N)]

for k in range(R):
    s=min(N,M)//2
    for j in range(s):
        x,y=j,j
        pre=com[x][y]
        #좌

        for i in range(x+1,N-j):

            tmp=com[i][j]
            com[i][j]=pre
            pre=tmp

        #하

        for i in range(y+1,M-j):

            tmp=com[N-1-j][i]
            com[N-1-j][i]=pre
            pre=tmp

        #우

        for i in range(N-j-2,j-1,-1):

            tmp=com[i][M-1-j]
            com[i][M-1-j]=pre
            pre=tmp
        #상

        for i in range(M-2-j,j-1,-1):

            tmp=com[j][i]
            com[j][i]=pre
            pre=tmp


for i in range(N):
    for j in range(M):
        print(com[i][j],end=' ')
    print()