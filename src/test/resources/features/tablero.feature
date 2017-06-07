# language: es
Característica: Tablero y alertas

  Escenario: Se agregan tareas estimadas a un tablero
    Dado un determinado tablero de proyecto con dos desarrolladores de dedicacion de "8" horas
    Dado una iteracion de proyecto que finaliza en "4" dias habiles
    Cuando Agrego una tarea estimada en "20" horas en la iteracion actual
    Cuando Agrego una tarea estimada en "20" horas en la iteracion actual
    Entonces la iteracion indica que hay "40" horas pendientes
    Entonces no hay alertas de retraso en la iteracion

  Escenario: Se agregan tareas estimadas a un tablero que exceden las horas disponibles de la iteracion
    Dado un determinado tablero de proyecto con dos desarrolladores de dedicacion de "8" horas
    Dado una iteracion de proyecto que finaliza en "4" dias habiles
    Cuando Agrego una tarea estimada en "40" horas en la iteracion actual
    Cuando Agrego una tarea estimada en "40" horas en la iteracion actual
    Entonces la iteracion indica que hay "80" horas pendientes
    Entonces existe una alerta "La iteracion se encuentra retrasada por 16 horas."

  Escenario: Se agregan tareas estimadas a un tablero que exceden las horas disponibles de la iteracion
    Dado un determinado tablero de proyecto con dos desarrolladores de dedicacion de "8" horas
    Dado una iteracion de proyecto que finaliza en "4" dias habiles
    Cuando Agrego una tarea estimada en "50" horas en la iteracion actual
    Cuando Agrego una tarea estimada en "50" horas en la iteracion actual
    Entonces la iteracion indica que hay "100" horas pendientes
    Entonces existe una alerta "La iteracion se encuentra retrasada por 36 horas."