import os
import struct
from socket import *

def parse_ip_header(payload):
    pre_ip_headers=struct.unpack("!BBHHHBBH4s4s",payload[:20])
    ihl=(pre_ip_headers[0]&0x0F)*4
    ip_payloads=payload[ihl:]
    return pre_ip_headers, ip_payloads

def parse_icmp_heaer(icmp_data):
    icmp_headers=struct.nupack("!BBHHH", icmp_data[:8])
    icmp_payloads=icmp_data[8:]
    return icmp_headers,icmp_payloads

def parse_tcp_header(payload):
    pre_tcp_headers=struct.unpack("!HHLLLBBHHH",payload[:20])
    tcp_header_length=(pre_tcp_headers[4]>>4)*4
    return pre_tcp_headers,payload[tcp_header_length:]

def parse_udp_header(payload):
    pre_udp_headers=struct.unpack("!HHHH",payload[:8])
    return pre_udp_headers,payload[8:]

