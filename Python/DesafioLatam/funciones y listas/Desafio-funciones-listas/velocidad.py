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



def promedio(lista):
        suma = 0 
        prom = 0
        retorno = 0
        for i in lista:
            if (type(i)==int or type(i)==float):
                suma+=int(i)
            else:
                suma+=0
        
        if suma == 0:
            retorno = "No existe promdio por que no son enteros o float"
        else:
            prom = suma /float(len(lista))
            retorno = prom
            
        return retorno
    
