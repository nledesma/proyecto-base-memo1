# language: es
Característica: Creacion de tarea

  Escenario: Nueva tarea
    Dado un determinado tablero de proyecto
    Cuando creo una tarea
    Entonces se crea una tarea con estado inicial pendiente

  Escenario: Nueva tarea incorrecta
    Dado un determinado tablero de proyecto
    Cuando creo una tarea sin descripcion
    Entonces se lanza una excepcion