# -*- coding: utf-8 -*-
"""
Created on Thu May 13 17:25:59 2021

@author: YonathanVasquez
"""

#importr sys
import sys


#ingreso de precio de venta
precio_venta = int(sys.argv[1])

#ingreso de usuarios
usuarios = int(sys.argv[2])

#ingreso de gastos
gastos = int(sys.argv[3])

#calcula utilidad 
utilidades = precio_venta*usuarios-gastos

#se concadena y muestra utilidad
print("La utilidad total es {}".format(utilidades))