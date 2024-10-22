class Examen(val nombres: Array<String>) {
    // Plantilla de respuestas correctas
    val plantilla: CharArray = charArrayOf('a', 'c', 'b', 'a', 'd', 'b', 'b', 'c', 'a', 'a', 'b', 'd')

    // Arreglo para guardar las notas de los estudiantes
    val notas: FloatArray = FloatArray(nombres.size)

    // Arreglo para guardar las respuestas de los estudiantes
    val respuestas: Array<CharArray> = Array(nombres.size) { CharArray(plantilla.size) }

    // Contador para saber cuántos estudiantes han respondido
    var contador: Int = 0

    // Función para leer las respuestas de un estudiante
    fun leerRespuestas(respuestasEstudiante: CharArray) {
        if (contador < respuestas.size) {
            respuestas[contador] = respuestasEstudiante
            contador++
        }
    }

    // Función para calcular las notas
    fun calculaNota() {
        for (i in respuestas.indices) {
            // Contamos cuántas respuestas son correctas
            val correctas = respuestas[i].indices.count { respuestas[i][it] == plantilla[it] }
            // Calculamos la nota como porcentaje
            notas[i] = (correctas.toFloat() / plantilla.size) * 100
        }
    }

    // Función para calcular el promedio de notas
    fun promedioGrupo(): Float {
        return notas.average().toFloat()
    }

    // Función para encontrar el estudiante con la mayor nota
    fun mayorNota(): String {
        val indiceMayor = notas.indices.maxByOrNull { notas[it] } ?: -1
        return if (indiceMayor != -1) nombres[indiceMayor] else ""
    }

    // Función para mostrar los resultados de cada estudiante
    override fun toString(): String {
        val resultado = StringBuilder()
        for (i in nombres.indices) {
            val estado = when {
                notas[i] >= 70 -> "Aprobó"
                notas[i] >= 60 -> "Aplazó"
                else -> "Reprobó"
            }
            resultado.append("Estudiante: ${nombres[i]} Respuestas: ${respuestas[i].joinToString(" ")} Nota: %.2f $estado\n".format(notas[i]))
        }
        return resultado.toString()
    }
}

fun main() {
    // Nombres de los estudiantes
    val nombres = arrayOf("Marta", "Pedro", "Juan", "María")

    // Respuestas de los estudiantes
    val respuestasEstudiantes = arrayOf(
        charArrayOf('a', 'c', 'b', 'a', 'd', 'b', 'b', 'c', 'a', 'a', 'b', 'd'),
        charArrayOf('b', 'c', 'b', 'd', 'd', 'b', 'b', 'a', 'b', 'd', 'b', 'd'),
        charArrayOf('c', 'c', 'b', 'a', 'a', 'b', 'c', 'c', 'a', 'a', 'b', 'c'),
        charArrayOf('c', 'c', 'b', 'a', 'd', 'b', 'b', 'c', 'a', 'a', 'b', 'c')
    )

    // Creamos una instancia de Examen
    val examen = Examen(nombres)

    // Leer respuestas de los estudiantes
    for (respuestas in respuestasEstudiantes) {
        examen.leerRespuestas(respuestas)
    }

    // Calcular las notas
    examen.calculaNota()

    // Mostrar los resultados
    println(examen.toString())
    println("Promedio del grupo: %.2f".format(examen.promedioGrupo()))
    println("El estudiante con mayor nota es ${examen.mayorNota()}.")
}