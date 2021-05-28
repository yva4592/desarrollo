# -*- coding: utf-8 -*-
"""
Created on Thu May 20 16:37:13 2021

@author: YonathanVasquez
"""

import string



valor = True
while(valor):
    
    password = input("Ingresa contraseña\n").lower()   

    
    if password.isalpha():
        
        
        ############################ Inicio fuerza bruta
        contador = 0
        list_letras_password = list(password)
        for a in range(len(list_letras_password)):
            list_abecedario = list(string.ascii_lowercase)
            for i in range(len(list_abecedario)):
                if list_letras_password[a] == list_abecedario[i]:
                    contador+=1
                    break
                else:
                    contador+=1            

        print(contador)
        ############################# Fin fuerza bruta
        valor = False
        
    else:
        valor = True
        print("La entrada es incorrecta: escribe una palabra alfabética") 