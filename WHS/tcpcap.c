#include <stdio.h>
#include <pcap.h>
#include <netinet/in.h>
#include <netinet/if_ether.h>
#include <arpa/inet.h>
#include "myheader.h"

//mac주소 표현 함수
void print_mac(u_char *mac) {
    for(int i = 0; i < 6; i++) {
        printf("%02x", mac[i]);
        if(i < 5) printf(":");
    }
}

void packetHandler(u_char *userData, const struct pcap_pkthdr* pkthdr, const u_char* packet) {
    struct ethheader *eth = (struct ethheader *) packet;
    
    if (ntohs(eth->ether_type) == 0x0800) { // IP Packet
        printf("MAC src: ");
        print_mac(eth->ether_shost);
        printf(" -> MAC dst: ");
        print_mac(eth->ether_dhost);
        printf("\n");
        
        //ipheader
        struct ipheader * ip = (struct ipheader *)(packet + sizeof(struct ethheader)); 
        
        if (ip->iph_protocol == IPPROTO_TCP) {
            printf("IP src: %s -> IP dst: %s\n", inet_ntoa(ip->iph_sourceip), inet_ntoa(ip->iph_destip));
            
            //tcp header
            struct tcpheader *tcp = (struct tcpheader *)(packet + sizeof(struct ethheader) + (ip->iph_ihl*4)); 

            printf("TCP src port: %d -> TCP dst port: %d\n", ntohs(tcp->tcp_sport), ntohs(tcp->tcp_dport));
            
            //나머지 내용 출력
            u_char *data = (u_char *)(packet + sizeof(struct ethheader) + (ip->iph_ihl*4) + (TH_OFF(tcp)*4));
            printf("MSG: %.16s\n\n", data);
        }
    }
}

int main() {
    pcap_t *descr;
    char errbuf[PCAP_ERRBUF_SIZE];
    char *dev;

    dev = pcap_lookupdev(errbuf);
    if (dev == NULL) {
        printf("pcap_lookupdev() failed: %s\n", errbuf);
        return 1;
    }

    descr = pcap_open_live(dev, BUFSIZ, 1, 1000, errbuf);
    if (descr == NULL) {
        printf("pcap_open_live() failed: %s\n", errbuf);
        return 1;
    }
    
    //packect Handler 실행
    if (pcap_loop(descr, 0, packetHandler, NULL) < 0) {
        fprintf(stderr, "\npcap_loop() failed: %s\n", pcap_geterr(descr));
        return 1;
    }

    return 0;
}
