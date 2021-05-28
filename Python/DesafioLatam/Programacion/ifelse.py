# -*- coding: utf-8 -*-
"""
Created on Mon May 17 18:08:24 2021

@author: YonathanVasquez
"""

import getpass
password = getpass.getpass("Ingrese Password:")

cant_carac = len(password)

if(cant_carac < 6):
    print("Tiene menos de 6 caracteres")
    if(password == "12345"):
        print("Password incorrecto")
else:
    print("Tiene 6 o más caracteres")

