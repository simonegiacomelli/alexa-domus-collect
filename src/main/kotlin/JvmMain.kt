import java.io.File
import java.net.URL


fun main() {

    val url = URL("http://10.0.0.19/ws?cmd=0,400,2000")
    val file = File("data/experiment1.txt")
    file.delete()
    cartesianProduct(setOf("7w", "14w"), setOf("NOISY", "CLEAN"), setOf("OFF", "ON"))
        .forEach { configurationList ->
            val configuration = configurationList.joinToString(",")
            println()
            println("Let's do $configuration")
            println("Press enter to start")
            readLine()
            println("Ok, starting.")
            (1..240).forEach { sequence ->
                println("Reading $configuration $sequence")
                url.readText()
                    .substringAfter("\n\n")
                    .replace("\n", "")
                    .let { values ->
                        val line = "$configuration,$sequence,values,$values"
                        println(line)
                        file.appendText("$line\n")
                    }
                Thread.sleep(500)
            }

        }


}


fun cartesianProduct(a: Set<*>, b: Set<*>, vararg sets: Set<*>): List<List<*>> =
    (setOf(a, b).plus(sets))
        .fold(listOf(listOf<Any?>())) { acc, set ->
            acc.flatMap { list -> set.map { element -> list + element } }
        }
        .toList()