# -*- coding: utf-8 -*-
"""
Created on Thu May 27 05:35:58 2021

@author: YonathanVasquez
"""



auto1 = ['Mazda RX4', 21.0, 6, False, 4]
auto2 = ['Merc 240D', 24.4, 4, True, 2]
auto3 = ['Merc 280', 19.2, 6, True, 4]
auto4 = ['Valiant', 18.1, 6, True, 1]
auto5 = ['Merc 450SL', 17.3, 8, False, 3]
auto6 = ['Toyota Corolla', 33.9, 4, True, 1]


autos = [auto1,auto2,auto3,auto4,auto5,auto6]
"""
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

"""
for auto in autos:
    prom = 0
    prom = promedio( [auto[1] if (type(auto[1])==int or type(auto[1])==float) else auto[1] for auto in autos ])
    if auto[1] > prom:
        print("La 2da columana: {}, es mayor al promedio de los autos: {}; en el auto: {}".format(auto[1],prom,auto[0]))

       