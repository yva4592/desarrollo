# -*- coding: utf-8 -*-
"""
Created on Thu May 27 06:38:47 2021

@author: YonathanVasquez
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

# funciom que retorna el promedio de una lista de numeros enteros y float


# liastas 

velocidad = [4, 4, 7, 7, 8, 9, 10, 10, 10,
11, 11, 12, 12, 12, 12, 13, 13,
13, 13, 14, 14, 14, 14, 15, 15,
15, 16, 16, 17, 17, 17, 18, 18,
18, 18, 19, 19, 19, 20, 20, 20,
20, 20, 22, 23, 24, 24, 24, 24, 25]


distancia = [2, 10, 4, 22, 16, 10, 18, 26, 34,
17, 28, 14, 20, 24, 28, 26, 34, 34,
46, 26, 36, 60, 80, 20, 26, 54, 32,
40, 32, 40, 50, 42, 56, 76, 84, 36,
46, 68, 32, 48, 52, 56, 64, 66, 54,
70, 92, 93, 120, 85]

# liastas

## Para el ejercicio se crea una matriz asociada con datos de velocidad y distancia
asociados = list(zip(velocidad, distancia))
## Para el ejercicio se crea una matriz asociada con datos de velocidad y distancia


################# Velocidad bajo el promedio

count_velocidad_bajo_promedio = 0
    
for e in range(len(asociados)):
    if asociados[e][0] < promedio(velocidad):
        count_velocidad_bajo_promedio+=1
print("La Velocidad bajo el promedio ocurre: {} veces.".format(count_velocidad_bajo_promedio))

################# Velocidad bajo el promedio


################# Velocidad bajo el promedio y distancia sobre el promedio.

coun_total = 0

for a in range(len(asociados)):
    if asociados[a][0] < promedio(velocidad) and asociados[a][1] > promedio(distancia):
        coun_total+=1               
print("La Velocidad bajo el promedio y distancia sobre el promedio ocurre: {} veces.".format(coun_total))

################# Velocidad bajo el promedio y distancia sobre el promedio.


################# Velocidad sobre el promedio

count_velocidad_sobre_promedio = 0
 
for i in range(len(asociados)):
    if asociados[i][0] > promedio(velocidad):
        count_velocidad_sobre_promedio+=1
print("La Velocidad sobre el promedio. ocurre: {} veces.".format(count_velocidad_sobre_promedio))

################# Velocidad sobre el promedio


################# Velocidad sobre el promedio y distancia bajo el promedio

coun_total_2 = 0

for j in range(len(asociados)):
    if asociados[j][0] > promedio(velocidad) and asociados[j][1] < promedio(distancia):
        coun_total_2+=1
              
print("La Velocidad bajo el promedio y distancia sobre el promedio ocurre: {} veces.".format(coun_total_2))


################# Velocidad sobre el promedio y distancia bajo el promedio