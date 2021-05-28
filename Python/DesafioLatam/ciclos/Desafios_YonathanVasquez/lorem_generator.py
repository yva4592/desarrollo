# -*- coding: utf-8 -*-
"""
Created on Tue May 18 19:29:54 2021

@author: YonathanVasquez
"""

  
import lorem


valor = True
while(valor):
    
    ###### Entrada de argumento o valor
    cantidad = input( "Ingrese el numero de parrados\n" )

    
    if cantidad.isnumeric():
        cantidad = int(cantidad)   
        
        
        ############################ Inicio listado de parrafos
        t = lorem.text()
        a = t.split("\n")
        for i in range(cantidad):
            print("{}\n\n".format(a[i]))
    
        ############################# Fin listado de parrafos
        valor = False
        
    else:
        valor = True
        print("La entrada es incorrecta: escribe un numero entero positivo") 
   

    
 
    
  