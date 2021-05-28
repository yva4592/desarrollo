# -*- coding: utf-8 -*-
"""
Created on Tue May 18 18:33:31 2021

@author: YonathanVasquez
"""

"""

cuenta_regresiva = int(input( "Ingrese un número para comenzar la cuenta\n"))

for i in range(cuenta_regresiva):
	tmp = cuenta_regresiva
	print( "Iteración {}" .format(tmp - i))

""" 

valor = True
while(valor):
    
    ###### Entrada de argumento o valor
    cuenta_regresiva = input( "Ingrese un número para comenzar la cuenta\n" )

    
    if cuenta_regresiva.isnumeric():
        cuenta_regresiva = int(cuenta_regresiva)   
        
        
        ############################ Inicio Conversion de for a while
        i = 0
        while i < cuenta_regresiva:
            tmp = cuenta_regresiva
            print( "Iteración {}" .format(tmp - i))
            i+=1
        ############################# Fin Conversion de for a while
        valor = False
        
    else:
        valor = True
        print("La entrada es incorrecta: escribe un numero entero positivo")         

