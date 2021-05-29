



# install  y use packages   -----------------------------------------------



 install.packages('ggplot2')



 library(dplyr)
 library(gapminder)
 library(ggplot2)


# CONDICONES   -----------------------------------------------



#gapminder %>%
#  filter(year == 2007)


#gapminder %>%
#  filter(country == 'United States')


  # gapminder %>%
    # filter(year == 2007,country == 'United States')


  # gapminder %>%
  # filter(year==2002,country == 'China')

  # gapminder  %>%
    # arrange(desc(lifeExp))


  # gapminder  %>%
    # arrange(lifeExp)


    # filter(year == 1957) %>%
    # arrange(desc(pop))


    # gapminder  %>%
      # mutate (pop = pop / 1000000)
    # arrange(desc(pop))


    # gapminder  %>%
      # mutate (gpd = gdpPercap * pop)


    # gapminder  %>%
    # mutate (gdp = gdpPercap * pop) %>%
    # filter(year == 2007) %>%
    # arrange(desc(gdp))











# GRAFICO DISPERSION  Y FILTER ----------------------------------------------------


      # gapminder_2007 <- gapminder  %>%
      # filter(year == 2007)

      # ggplot (gapminder_2007, aes( x = pop, y = gdpPercap)) +
      # geom_point()

      # ggplot (gapminder_2007, aes( x = gdpPercap, y = lifeExp)) +
      # geom_point() +
      # scale_x_log10()



      # gapminder_1952 <- gapminder %>%
      #   filter(year == 2007)


      # ggplot(gapminder_1952, aes(x = pop, y = lifeExp)) +
      # geom_point()+
      # scale_x_log10()


     # gapminder_1952 <- gapminder %>%
     #   filter(year >= 1952 | year <= 2007)
     #
     #
     # ggplot(gapminder_1952, aes(x = gdpPercap, y = lifeExp , color = continent,
     #                size = pop)) +
     # geom_point()+
     # scale_x_log10() +
     #   facet_wrap(~year)


    # gapminder_1952 <- gapminder %>%
    #   filter(year == 1952)
    #
    #  ggplot(gapminder_1952, aes(x = pop, y = lifeExp , color = continent,
    #                             size = gdpPercap)) +
    #    geom_point()+
    #    scale_x_log10() +
    #    facet_wrap(~continent)





 #     gapminder_2007 <- gapminder %>%
 #       filter(year == 2007)
 #
 # ggplot(gapminder_2007, aes(x = gdpPercap, y = lifeExp, color = continent,
 #                            size = pop)) +
 #   geom_point()+
 #   scale_x_log10() +
 # facet_wrap(~continent)



# SUMMARIZE - MEDIAN, MAX, MIN, SUM, MEAN---------------------------------------------------------------




      # gapminder %>%
      # filter(year == 2007) %>%
      # summarize (meanLifeExp = mean(lifeExp),
      # totalPop = sum(pop))

        # gapminder %>%
        # filter(year == 1957) %>%
        # summarize (medianLifeExp = median(lifeExp),
        #            maxGdpPercap = max(gdpPercap))

        # gapminder %>%
        #   group_by(year) %>%
        #   summarize (medianLifeExp = median(lifeExp),
        #              maxGdpPercap = max(gdpPercap))


        # gapminder %>%
        # filter(year == 2007) %>%
        # group_by(continent) %>%
        # summarize (meanLifeExp = mean(lifeExp),
        #            totalPop = sum(pop))


        # gapminder %>%
        #   group_by(year,continent) %>%
        #   summarize (medianLifeExp = median(lifeExp),
        #                    maxGdpPercap = max(gdpPercap))




        # gapminder %>%
        #   filter(year == 1957) %>%
        #   group_by(continent) %>%
        #   summarize (medianLifeExp = median(lifeExp),
        #              maxGdpPercap = max(gdpPercap))




# GRAFICO DISPERSION (PUNTOS) -----------------------------------------------------




      # by_year  <- gapminder %>%
      #     group_by(year) %>%
      #     summarize (totalPop = sum(pop),
      #                meanLifeExp = mean(lifeExp))
      #
      # ggplot (by_year,  aes(x = year, y = totalPop) ) +
      #   geom_point()  +
      #   expand_limits(y = 0)



      # ggplot (by_year,  aes(x = year, y = medianLifeExp) ) +
      # geom_point()  +
      # expand_limits(y = 0)


      # by_year_continent  <- gapminder %>%
      #   group_by(year,continent) %>%
      #   summarize (totalPop = sum(pop),
      #              meanLifeExp = mean(lifeExp))
      #
      # ggplot (by_year_continent,  aes(x = year, y = totalPop, color = continent) ) +
      #   geom_point()  +
      #   expand_limits(y = 0)



      # by_year_continent  <- gapminder %>%
      #   group_by(continent,year) %>%
      #   summarize (medianGdpPercap = median(gdpPercap))
      #
      #
      # ggplot (by_year_continent,  aes(x = year, y = medianGdpPercap, color = continent) ) +
      #   geom_point()  +
      #   expand_limits(y = 0)
      #
      #
      #




      # by_continent_2007  <-  gapminder %>%
      #   filter(year == 2007) %>%
      #   group_by(continent) %>%
      #   summarize (medianGdpPercap = median(gdpPercap),
      #              medianLifeExp = median(lifeExp))
      #
      #
      #
      # ggplot (by_continent_2007,  aes(x = medianGdpPercap, y = medianLifeExp, color = continent) ) +
      #   geom_point() +
      #   expand_limits(y = 0)



# GRAFICO LINEAL ------------------------------------------------------------------




      # year_continent  <- gapminder %>%
      #   group_by(year,continent) %>%
      #   summarize (totalPop = sum(pop),
      #              meanLifeExp = mean(lifeExp))
      #
      # ggplot (year_continent,  aes(x = year, y = meanLifeExp, color = continent) ) +
      #   geom_line()  +
      #   expand_limits(y = 0)




        # by_year  <-  gapminder %>%
        # group_by(year) %>%
        # summarize (medianGdpPercap = median(gdpPercap))
        #
        #
        #
        # ggplot (by_year,  aes(x = year, y = medianGdpPercap) ) +
        # geom_line() +
        # expand_limits(y = 0)




        #   by_year_continent  <- gapminder %>%
        #   group_by(year,continent) %>%
        #   summarize (medianGdpPercap = median(gdpPercap))
        #
        # ggplot (by_year_continent,  aes(x = year, y = medianGdpPercap, color = continent) ) +
        #   geom_line()  +
        #   expand_limits(y = 0)

# GRAFICO COLUMNAS ----------------------------------------------------------------


        # by_continent  <- gapminder %>%
        # filter(year == 2007) %>%
        # group_by(continent) %>%
        # summarize (meanLifeExp = mean(lifeExp))
        #
        #
        #
        # ggplot (by_continent,  aes(x = continent, y = meanLifeExp) ) +
        #   geom_col()








        # oceania_1952  <- gapminder %>%
        # filter(year == 1952, continent == 'Oceania')
        #
        # ggplot (oceania_1952,  aes(x = country, y = gdpPercap) ) +
        # geom_col()



# GRAFICO HISTOGRAMAS -------------------------------------------------------------



        # gapminder_2007 <- gapminder %>%
        #  filter(year == 2007)
        #
        # ggplot(gapminder_2007, aes(x = lifeExp)) +
        #   geom_histogram(binwidth = 5)





        # gapminder_1952 <- gapminder %>%
        # filter(year == 1952) %>%
        # mutate(pop_by_mil = pop / 1000000)
        #
        # ggplot(gapminder_1952, aes(x = pop_by_mil)) +
        # geom_histogram(binS = 50)



          # gapminder_1952 <- gapminder %>%
          # filter(year == 1952)
          #
          # ggplot(gapminder_1952, aes(x = pop)) +
          # geom_histogram() +
          # scale_x_log10()


# DIAGRAMA DE CAJAS -------------------------------------------------------



          gapminder_2007 <- gapminder %>%
            filter(year == 2007)


          ggplot(gapminder_2007, aes(x = continent  ,y = lifeExp)) +
            geom_boxplot()



          gapminder_1952 <- gapminder %>%
          filter(year == 1952)

          ggplot(gapminder_1952, aes(x = continent,y = gdpPercap)) +
          geom_boxplot()  +
          scale_y_log10() +
          ggtitle ("diagrama de cajas")

          # remove(gapminder_2007)