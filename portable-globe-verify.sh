#!/bin/bash
#
# Will verify if a Google Earth Enterprise globe or map (*.glb, *.glm) file is
# valid. This script should work on both Mac and Linux.
#
# Usage: portable-globe-verify.sh path/to/globe.glb
#
# Copyright 2013 Jordon Mears <jordoncm@gmail.com>
#
# Licensed under the Apache License, Version 2.0 (the "License"); you may not
# use this file except in compliance with the License. You may obtain a copy of
# the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
# WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
# License for the specific language governing permissions and limitations under
# the License.

USAGE="Usage: portable-globe-verify.sh path/to/globe.glb"

if [ $# -ne 1 ]; then
    echo $USAGE
    exit 1
fi

FILENAME=$1
FILESIZE=0

if [ `uname` == 'Darwin' ]; then
    FILESIZE=$(stat -f %z "$FILENAME")
else
    FILESIZE=$(stat -c%s "$FILENAME")
fi
echo -n "Verifying globe..."

OFFSET=`expr $FILESIZE - 8`
HEADER=$(hexdump -C -s "$OFFSET" "$FILENAME")

if [[ $HEADER =~ .*GEEG.* ]]; then
    echo " VALID"
else
    echo " NOT VALID"
fi
