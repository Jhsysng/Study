from socket import *
import time

serverName = '127.0.0.1'
serverPort = 12000

clientSocket = socket(AF_INET, SOCK_DGRAM)
clientSocket.settimeout(1)

rtts = []

for sequence_number in range(1, 11):
    message = f'Ping {sequence_number} {time.time()}'
    try:
        clientSocket.sendto(message.encode(), (serverName, serverPort))
        send_time = time.time()
        modifiedMessage, serverAddress = clientSocket.recvfrom(1024)
        recv_time = time.time()
        rtt = recv_time - send_time
        rtts.append(rtt)
        print(f'Reply from {serverAddress}: seq={sequence_number} time={rtt}s')
    except timeout:
        print('Request timed out')

if rtts:
    min_rtt = min(rtts)
    max_rtt = max(rtts)
    avg_rtt = sum(rtts) / len(rtts)
    loss_rate = (10 - len(rtts)) / 10 * 100

    print(f'Minimum RTT: {min_rtt}s')
    print(f'Maximum RTT: {max_rtt}s')
    print(f'Average RTT: {avg_rtt}s')
    print(f'Packet Loss Rate: {loss_rate}%')

clientSocket.close()