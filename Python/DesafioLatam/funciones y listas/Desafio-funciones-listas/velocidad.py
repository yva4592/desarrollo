# -*- coding: utf-8 -*-
"""
Created on Thu May 27 02:16:56 2021

@author: YonathanVasquez
"""

velocidad = [4, 4, 7, 7, 8, 9, 10, 10, 10,
11, 11, 12, 12, 12, 12, 13, 13,
13, 13, 14, 14, 14, 14, 15, 15,
15, 16, 16, 17, 17, 17, 18, 18,
18, 18, 19, 19, 19, 20, 20, 20,
20, 20, 22, 23, 24, 24, 24, 24, 25]


# funciom que retorna el promedio de una lista de numeros enteros y float
def promedio(lista):
        suma = 0 
        prom = 0
        retorno = 0
        string = ""
        for i in lista:
            if (type(i)==int or type(i)==float):
                suma+=int(i)
            else:
                string = "si"
                break
        
        if string == "si":
            retorno = "No tiene promedio por que la lista no es de campos numericos !! "
        else:
            prom = suma /float(len(lista))
            retorno = prom
            
        return retorno
    
