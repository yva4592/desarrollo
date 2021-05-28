# -*- coding: utf-8 -*-
"""
Created on Tue May 18 18:42:39 2021

@author: YonathanVasquez
"""


        
valor = True
while(valor):
    
    ###### Entrada de argumento o valor
    cuenta_regresiva = input( "Ingrese un número para comenzar la cuenta\n" )

    
    if cuenta_regresiva.isnumeric():
        cuenta_regresiva = int(cuenta_regresiva)   
        
        
        ############################ Inicio solo par refactor
        for a in range(1,cuenta_regresiva+1):
            if a%2 == 0:
                print(a)
        
        ############################# Fin solo par refactor
        valor = False
        
    else:
        valor = True
        print("La entrada es incorrecta: escribe un numero entero positivo") 
   