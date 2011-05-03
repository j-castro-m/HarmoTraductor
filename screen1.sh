#!/bin/bash

# Tablet PC Mode Script tabletpcmode.sh
# Made for the Toshiba Sattelite R15-S822
# Running Ubuntu Edgy Eft 6.10
#
# Last Updated: 11-18-2006
# Maintained by Justin "Linuturk" Phelps
# linuturk@gmail.com
# 
# Move this script to /usr/local/bin
# Run 'sudo chmod +x tabletpcmode.sh'
# Be sure laptopmode.sh is installed as well

#xrandr -o right && xsetwacom set stylus Rotate CCCW

orientation=`xrandr -q | grep -c 1280x800`

/usr/bin/X11/xrandr --orientation right
xsetwacom set "Serial Wacom Tablet stylus" Rotate CCW	
xsetwacom set "Serial Wacom Tablet eraser" Rotate CCW
xsetwacom set "Serial Wacom Tablet stylus" xyDefault
xsetwacom set "Serial Wacom Tablet eraser" xyDefault



mv /home/alu03009/scripts/screen1.sh /home/alu03009/scripts/klear.sh
mv /home/alu03009/scripts/screen2.sh /home/alu03009/scripts/screen1.sh
mv /home/alu03009/scripts/klear.sh /home/alu03009/scripts/screen2.sh

exit 0

