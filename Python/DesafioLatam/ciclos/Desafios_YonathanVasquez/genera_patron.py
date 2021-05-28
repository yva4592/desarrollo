# -*- coding: utf-8 -*-
"""
Created on Tue May 18 19:21:20 2021

@author: YonathanVasquez
"""




    
    
valor = True
while(valor):
    
    ###### Entrada de argumento o valor
    cuenta_regresiva = input( "Ingrese un número para comenzar la cuenta\n" )

    
    if cuenta_regresiva.isnumeric():
        cuenta_regresiva = int(cuenta_regresiva)   
        
        
        ############################ Inico genera patron
        b = ""
        for a in range(1,cuenta_regresiva+1):
            b+=str(a)
            print(b)
        ############################# Fin genera patron
        valor = False
        
    else:
        valor = True
        print("La entrada es incorrecta: escribe un numero entero positivo")