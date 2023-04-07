import sys
T = int(sys.stdin.readline().strip())

for t in range(T):
    M,N,K = map(int,sys.stdin.readline().split())
    arr=[[0]*N for _ in range(M)]
    for k in range(K):
        X,Y = map(int, sys.stdin.readline().split())
        arr[X][Y]=1

    cnt=0 #count
    for y in range(N):
        for x in range(M):
            flag = True
            if y == 0:
                if x==0:
                    if arr[x][y]==1:
                        arr[x][y]+=1
                        if (arr[x+1][y]!=2 and arr[x][y+1]!=2):
                            cnt+=1
                elif x==M-1:
                    if arr[x][y]==1:
                        if (arr[x][y+1]!=2 and arr[x-1][y]!=2):
                            cnt+=1
                        arr[x][y] = 2
                else:
                    if arr[x][y]==1:
                        arr[x][y]+=1
                        if arr[x][y-1]!=2 and arr[x][y+1]!=2 and arr[x-1][y]!=2:
                            cnt+=1
            elif y == N-1:
                if x==0:
                    if arr[x][y]==1:
                        arr[x][y]+=1
                        if(arr[x+1][y]!=2 and arr[x][y-1]!=2):
                            cnt+=1
                elif x==M-1:
                    if arr[x][y]==1:
                        arr[x][y]+=1
                        if(arr[x-1][y]!=2 and arr[x][y+1]):
                            cnt+=1
                else:
                    if arr[x][y]==1:
                        arr[x][y]+=1
                        if (arr[x][y-1]!=2 and arr[x+1][y]!=2 and arr[x-1][y]!=2):
                            cnt+=1
                        
            else:
                if x==0:
                    if arr[x][y]==1:
                        arr[x][y]+=1
                        if(arr[x][y-1]!=2 and arr[x+1][y]!=2 and arr[x][y+1]!=2):
                            cnt+=1
                elif x==M-1:
                    if arr[x][y]==1:
                        arr[x][y]+=1
                        if(arr[x][y-1]!=2 and arr[x][y+1]!=2 and arr[x-1][y]!=2):
                            cnt+=1
                else:
                    if arr[x][y]==1:
                        arr[x][y]+=1
                        if arr[x][y-1]!=2 and arr[x][y+1]!=2 and arr[x-1][y]!=2 and arr[x+1][y]!=2:
                            cnt+=1
    print(cnt)

                #y 0 -> x=0 우 하, x=1~M-1 좌 우 하, x=M-1 좌 하, 
                #y N-1 -> x=0 우 상, x=1~M-1 좌 우 상, X=M-1 좌 상 , 
                #y 1~N-1-> x=0 상 우 하,x=1~M-1 상하좌우, x=M-1 좌 상 하, 