# language: es
Característica: Creacion de tarea

  Escenario: Nueva tarea
    Dado un determinado tablero de proyecto
    Cuando creo una tarea de desarrollo con descripcion "una tarea" y sin responsable
    Entonces se crea una tarea con estado inicial pendiente
    Entonces la tarea creada tiene la descripcion "una tarea"
    Entonces el lider de proyecto es el responsable de la tarea

  Escenario: Nueva tarea sin descripcion
    Dado un determinado tablero de proyecto
    Cuando creo una tarea de desarrollo sin descripcion
    Entonces se lanza una excepcion con mensaje "El campo descripcion es obligatorio"

  Escenario: Nueva tarea sin tipo
    Dado un determinado tablero de proyecto
    Cuando creo una tarea de desarrollo sin especificar el tipo
    Entonces se lanza una excepcion con mensaje "El campo tipo tarea es obligatorio"

  Escenario: Nueva tarea de soporte sin ticket
    Dado un determinado tablero de proyecto
    Cuando creo una tarea de soporte sin ticket asociado
    Entonces se lanza una excepcion con mensaje "La tarea de soporte debe estar vinculada a un ticket"

  Escenario: Nueva tarea sin autorizar
    Dado un determinado tablero de proyecto
    Cuando un empleado sin autorizar crea una tarea
    Entonces se lanza una excepcion con mensaje "El usuario no puede crear esta tarea"