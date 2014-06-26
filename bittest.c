#include<stdio.h>
#include<stdlib.h>
#include<string.h>
typedef unsigned char U8;
typedef unsigned short U16;
typedef union{
   char byte[2];
   U16 tlv;
}tlvType;
int main()
{
   U8 bits7 = 3 << 1;
   tlvType tType;
   memset(&tType, 0, sizeof(tlvType));

   tType.byte[0] = tType.byte[0] | bits7;
   printf("%02x %02x\n", tType.byte[0], tType.byte[1]);
   printf("tlv %u\n", tType.tlv);

   U16 length = 256;
   tType.tlv = tType.tlv | htons(length);

   printf("%02x %02x\n", tType.byte[0], tType.byte[1]);
   printf("tlv %u\n", tType.tlv);
   return 0;
}

   
