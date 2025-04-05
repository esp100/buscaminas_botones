# Buscaminas en Java con Botones

>**Fecha de Creación Original:** Noviembre de 2020

## Descripción

Este proyecto implementa el juego de Buscaminas utilizando botones en una interfaz gráfica de Java Swing. El juego consiste en descubrir todas las casillas del tablero que no contienen minas. Al hacer clic en una casilla que no tiene una mina, se revela un número que indica la cantidad de minas adyacentes. Si se hace clic en una casilla con una mina, el juego termina.

## Flujo de Ejecución / Juego

1.  **Inicio:** Al iniciar, se presenta un tablero de 8x8 botones. Algunas de estas casillas contienen minas ocultas. Esta implementación coloca 15 minas en el tablero.
2.  **Descubrimiento:** Al hacer clic en un botón para revelar su contenido.
    * Si el botón no contiene una mina, mostrará un número indicando cuántas minas hay en las casillas adyacentes (arriba, abajo, izquierda, derecha y las cuatro diagonales según sea el caso).
    * Si el botón contiene una mina, se pasa al estado de derrota.
3.  **Marcado:** Aunque no se implementa la acción de marcar con banderas directamente en la interacción principal, el estado de victoria se indica visualmente cambiando los botones a imágenes de banderas.
4.  **Victoria:** El jugador gana cuando todas las casillas que no contienen minas han sido descubiertas. En este estado, todos los botones de minas se transformarán en imágenes de banderas.
5.  **Derrota:** El jugador pierde si hace clic en una casilla que contiene una mina. En este estado, todos los botones que contenían minas se revelarán con imágenes de bombas.
6.  **Reiniciar:** Se implementó el botón "Reiniciar" en la parte superior de la ventana para comenzar una nueva partida.

## Estados del Juego (Visualizaciones)

### Conteo y Descubrimiento

A medida que el jugador hace clic en las casillas sin minas, los botones mostrarán el número de minas adyacentes.

![Conteo y descubrimiento de casillas](/test-gif/Descubrir.gif ) 

### Estado de Victoria

Cuando el jugador descubre todas las casillas seguras, los botones que contenian minas en el tablero cambiarán a imágenes de banderas, indicando la victoria.

![Estado de victoria (cambio a banderas)](/test-gif/Victoria.gif)

### Estado de Derrota

Si el jugador hace clic en una mina, todas las minas ocultas se revelarán con imágenes de bombas, indicando la derrota.

![Estado de derrota (cambio a minas)](/test-gif/Muerte.gif) 


