# -*- coding: utf-8 -*-
"""
Created on Thu May 13 18:49:20 2021

@author: YonathanVasquez
"""

#importr sys
import sys


#ingreso de argumento1
argumento1 = int(sys.argv[1])

#ingreso de argumento2
argumento2 = int(sys.argv[2])

#ingreso de argumento3
argumento3 = int(sys.argv[3])



if argumento1 > argumento2:
	if argumento1 > argumento3:
            print(argumento1)
            
if argumento2 > argumento1:
	if argumento2 > argumento3:
            print(argumento2)
            
if argumento3 > argumento1:
	if argumento3 > argumento2:
            print(argumento3)