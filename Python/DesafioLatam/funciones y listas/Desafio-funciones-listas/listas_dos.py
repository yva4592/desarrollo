# -*- coding: utf-8 -*-
"""
Created on Thu May 27 05:32:21 2021

@author: YonathanVasquez
"""



auto1 = ['Mazda RX4', 21.0, 6, False, 4]
auto2 = ['Merc 240D', 24.4, 4, True, 2]
auto3 = ['Merc 280', 19.2, 6, True, 4]
auto4 = ['Valiant', 18.1, 6, True, 1]
auto5 = ['Merc 450SL', 17.3, 8, False, 3]
auto6 = ['Toyota Corolla', 33.9, 4, True, 1]


autos = [auto1,auto2,auto3,auto4,auto5,auto6]



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


for auto in autos:
    for i in range(len(auto)):
        print("{}.-La columna {} de los autos anidados tiene como promedio: {} ".format(i+1,i, promedio( [auto[i] if (type(auto[i])==int or type(auto[i])==float) else auto[i] for auto in autos ])))
    break   
