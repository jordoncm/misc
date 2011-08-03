#!/usr/bin/env python

"""
Sets the UUID of a VirtualBox VDI Hard disk to a UUID string given on the 
command line.

USAGE: ./set-uuid.py UUID PATH/TO/VDI

Copyright (c) 2011, Jordon Mears <http://www.finefrog.com/>
All rights reserved.

Redistribution and use in source and binary forms, with or without 
modification, are permitted provided that the following conditions are met:

Redistributions of source code must retain the above copyright notice, this 
list of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this 
list of conditions and the following disclaimer in the documentation and/or 
other materials provided with the distribution.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND 
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED 
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE 
DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE 
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL 
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR 
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER 
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, 
OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE 
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
"""

import binascii
import os
import re
import struct
import sys

newUuid = sys.argv[1].replace('-', '').replace('{', '').replace('}', '')
if len(newUuid) != 32 or re.search(r'[^0-9a-f]', newUuid) != None:
    print 'ERROR: Invalid UUID given.'
    sys.exit(1)

file = open(sys.argv[2], 'r+b')
file.seek(392, os.SEEK_SET)
uuid = file.read(16)
uuid = struct.unpack(
    '8s8s',
    uuid
)
tmp = struct.unpack('<IHH', uuid[0])
uuid = tmp + struct.unpack('!HIH', uuid[1])
print uuid
prettyUuid = '{%08x-%04x-%04x-%04x-%08x%04x}' % (
    uuid[0],
    uuid[1],
    uuid[2],
    uuid[3],
    uuid[4],
    uuid[5]
)
print 'UUID was : ' + prettyUuid

file.seek(392, os.SEEK_SET)

file.write(binascii.a2b_hex(newUuid[6] + newUuid[7]))
file.write(binascii.a2b_hex(newUuid[4] + newUuid[5]))
file.write(binascii.a2b_hex(newUuid[2] + newUuid[3]))
file.write(binascii.a2b_hex(newUuid[0] + newUuid[1]))

file.write(binascii.a2b_hex(newUuid[10] + newUuid[11]))
file.write(binascii.a2b_hex(newUuid[8] + newUuid[9]))

file.write(binascii.a2b_hex(newUuid[14] + newUuid[15]))
file.write(binascii.a2b_hex(newUuid[12] + newUuid[13]))

file.write(binascii.a2b_hex(newUuid[16] + newUuid[17]))
file.write(binascii.a2b_hex(newUuid[18] + newUuid[19]))

file.write(binascii.a2b_hex(newUuid[20] + newUuid[21]))
file.write(binascii.a2b_hex(newUuid[22] + newUuid[23]))
file.write(binascii.a2b_hex(newUuid[24] + newUuid[25]))
file.write(binascii.a2b_hex(newUuid[26] + newUuid[27]))
file.write(binascii.a2b_hex(newUuid[28] + newUuid[29]))
file.write(binascii.a2b_hex(newUuid[30] + newUuid[31]))

file.close()

prettyNewUuid = '{%s-%s-%s-%s-%s}' % (
    newUuid[0:8],
    newUuid[8:12],
    newUuid[12:16],
    newUuid[16:20],
    newUuid[20:]
)

print 'UUID is  : ' + prettyNewUuid
