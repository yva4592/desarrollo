# -*- coding: utf-8 -*-
"""
Created on Thu May 13 17:54:23 2021

@author: YonathanVasquez
"""

#importr sys
import sys


#ingreso de precio de venta
precio_venta = int(sys.argv[1])

#cantidad de usuarios
cantidad_usuarios = int(sys.argv[2])

#ingreso de gastos
gastos = int(sys.argv[3])

#ingreso de utilidades del año anterior
utilidad_ano_anterior = sys.argv[4]

utilidad = utilidad_ano_anterior.isdigit()


 #operacion de utilidad del año anterior
if int(utilidad_ano_anterior)==0:
    utilidad = int("1000")
    
if  utilidad==False:
       utilidad = int("1000")


#utilidad de anño actual
utilidad_ano_actual = precio_venta*cantidad_usuarios - gastos

# razon entre utilidad año actual y utilidad año anterior
razon = utilidad_ano_actual / utilidad


#se concadena y muestra utilidades de los tipos de usuarios
print("La utilidad actual es {} siendo la razon con la utilidad del año anterior de {}".format(utilidad_ano_actual,razon))
