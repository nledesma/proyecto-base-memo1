# language: es
Característica: Creacion de tarea

  Escenario: Nueva tarea
    Dado un determinado tablero de proyecto
    Cuando creo una tarea con descripcion "una tarea" y sin responsable
    Entonces se crea una tarea con estado inicial pendiente
    Entonces la tarea creada tiene la descripcion "una tarea"
    Entonces el lider de proyecto es el responsable de la tarea

  Escenario: Nueva tarea incorrecta
    Dado un determinado tablero de proyecto
    Cuando creo una tarea sin descripcion
    Entonces se lanza una excepcion

  Escenario: Nueva tarea sin autorizar
    Dado un determinado tablero de proyecto
    Cuando un empleado sin autorizar crea una tarea
    Entonces se lanza una excepcion