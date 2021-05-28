# -*- coding: utf-8 -*-
"""
Created on Thu May 13 18:17:11 2021

@author: YonathanVasquez
"""

#importr sys
import sys
from random import randrange



#ingreso de argumento
argumento_ingresado = sys.argv[1]

valor_randon = randrange(1,4,1)


if(valor_randon == 1):
    print("Computador juega piedra")

if(valor_randon == 2):
    print("Computador juega papel")
    
if(valor_randon == 3):
    print("Computador juega tijera")
    
    
valor_ingresado_piedra = 0
valor_ingresado_papel = 0
valor_ingresado_tijera = 0
    
    
if(argumento_ingresado == "piedra"):
    valor_ingresado_piedra = 1

if(argumento_ingresado == "papel"):
    valor_ingresado_papel = 2
    
if(argumento_ingresado == "tijera"):
    valor_ingresado_tijera = 3
    
    

if valor_ingresado_piedra == valor_randon or valor_ingresado_papel == valor_randon or valor_ingresado_tijera == valor_randon:
            print("Empataste")

            
if valor_ingresado_piedra == 1 and valor_randon == 2:
            print("Perdiste")

if valor_ingresado_piedra == 1 and valor_randon == 3:
            print("Ganaste")
            

            
if valor_ingresado_papel == 2 and valor_randon == 1:
            print("Ganaste")

if valor_ingresado_papel == 1 and valor_randon == 2:
            print("Persite")            

            


if valor_ingresado_tijera == 3 and valor_randon == 1:
            print("Persite")

if valor_ingresado_tijera == 3 and valor_randon == 2:
            print("Ganaste") 
                 
                 
        
