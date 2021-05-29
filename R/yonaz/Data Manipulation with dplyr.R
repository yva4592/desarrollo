# install  y use packages   -----------------------------------------------
# Usar version de R 3.4.4 es la que usa DataCamp para el uso datos de US


 install.packages('Rdata')



library(dplyr)
library(gapminder)
library(ggplot2)
library(tigris)
library(sf)

me <- counties("Maine", cb = TRUE)

gg <- ggplot()
gg <- gg + geom_sf(data = me, color="black",
                   fill="white", size=0.25)




