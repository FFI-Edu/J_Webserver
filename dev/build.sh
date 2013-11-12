#!/bin/bash
# first compile to class files, then link them
# see: http://stackoverflow.com/questions/2567230/gcj-creates-duplicate-dummy-symbol
gcj -I dev -C *.java -Wno-all
gcj -I dev --main=MAIN *.class -Wno-all
# -ftarget=1.7 produces:
# target level should be comprised in between '1.1' and '1.7' (or '5', '5.0', ..., '7' or '7.0') or cldc1.1: 7
