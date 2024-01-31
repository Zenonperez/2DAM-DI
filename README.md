# 2DAM-DI
Trabajo de Desarollo de Interfaces
Actividad 2 parte 1: Proyecto Entreno 1.2:
  - Se ha creado un Label que es animado, este tiene 3 propiedades diferentes al Label original. Sus propiedades son AppendedText, usado para poner un texto que se usara en el programa para aparecer y desaparecer como si estuviese animado; el delay, es un parametro int que determinara con que frecuencia se repide el timer para animar el texto, y por ultimo tenemos el animated, un boolean que si esta en true realizara la animación y por el contrario la animación no se realizara. Este JavaBean esta implementado en el proyecto para poder usarlo hay que hacer lo siguiente: Click derecho en AnimatedLabel.java > Tools > add to Palette > eliges el sitio donde quieres añadirlo y lo utilizas donde quieras luego hay que implementarlo dentro de donde se ponga usando un timer.

Actividad 2 parte 2: Proyecto Entreno 1.3:

-Se ha eliminado la carpeta "bean" ahora cada bean tiene sus proyectos independientes.
-Se ha implementado el JavaBean "CustomComponentEjercicio" en la tarea para demostrar que funciona correctamente dentro del programa.
-Se han arreglado ciertos bugs como que el programa no se terminaba de cerrar y ahora unicamente se cierra si desde Login, se cierra la ventana.
-El JavaBean implentado "CustomComponentEjercicio" es capaz de notificar por pantalla si se ha arrastrado el panel hacia la izquierda o hacia a la derecha además de regresar el numero de la id del ejercicio al que esta vinculado, que de momento esta "1" por ser default.

Actividad 2 parte 3: Proyecto 2.0:
-Se han eliminado el uso de tablas de intentos, tanto intentos pendientes como intentos usuarios, se han eliminado por que ahora en vez de tablas se utilizara el bean que hemos desarollado (CustomComponentEjercicio) para realizara las funciones que hacia la tabla que era representar los intentos. 
-Con este componente si lo deslizamos a la derecha podermos ver el video que tiene ese intento. Si lo deslizamos hacia la izquierda se activaran los botones pudiendo hacer otras gestiones como eliminar la review de un intento o valorar un intento sin review entre otras.
-Se ha cambiado un poco la interfaz grafica ya que los componenetes sino no se mostraban en el caso que hubieran varios, es por esos que ahora los usuarios y los intentos pendientes estan en un tabbed pane, para tener más espacio.

Actividad 3 parte 1: Fitnow 3.0

-El proyecto empieza a tener forma y ahora tiene un estilo grafico de las ventanas dentro del programa de color gris oscuras dandole un toque estetico a la aplicacion
- Se han arreglado multiples bugs que tenia el programa al seleccionar un intento, ya que miraba la id del ejercico y no la del intento.ç
- Se han agragado imagenes e iconos para mejorar la estetica del programa.
