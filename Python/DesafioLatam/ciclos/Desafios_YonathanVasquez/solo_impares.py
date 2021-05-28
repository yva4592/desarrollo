# -*- coding: utf-8 -*-
"""
Created on Tue May 18 19:04:52 2021

@author: YonathanVasquez
"""


valor = True
while(valor):
    
    ###### Entrada de argumento o valor
    cuenta_regresiva = input( "Ingrese un número para comenzar la cuenta\n" )

    
    if cuenta_regresiva.isnumeric():
        cuenta_regresiva = int(cuenta_regresiva)   
        
        
        ############################ Inico solo impar
        for a in range(1,cuenta_regresiva+1):
            if a%2 != 0:
                print(a)
        ############################# Fin solo impar
        valor = False
        
    else:
        valor = True
        print("La entrada es incorrecta: escribe un numero entero positivo") 
    