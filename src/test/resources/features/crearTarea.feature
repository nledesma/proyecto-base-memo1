# language: es
Característica: Creacion de tarea

  Escenario: Nueva tarea
    Dado un determinado tablero de proyecto
    Cuando creo una tarea con descripcion "una tarea"
    Entonces se crea una tarea con estado inicial pendiente
    Entonces la tarea creada tiene la descripcion "una tarea"

  Escenario: Nueva tarea incorrecta
    Dado un determinado tablero de proyecto
    Cuando creo una tarea sin descripcion
    Entonces se lanza una excepcion