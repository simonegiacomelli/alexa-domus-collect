import java.io.File
import kotlin.math.round


fun main() {
    val file = File("data/experiment.txt")
    file.bufferedReader().useLines { lines ->
        lines.forEach { line ->
            val fields = line.split(",")
            val signal = fields[5].trim().split(" ").map { it.toInt() }
            val average = signal.windowed(5, 2) { it.average() }
            val derivative1 = average.windowed(2, 2) { round((it[1] - it[0])*10)/10 }
            val derivative2 = derivative1.windowed(2, 2) { round((it[1] - it[0])*10)/10 }
            print(fields.take(4))
            println("average=$average")
            println("derivative1=$derivative1")
            println("derivative2=$derivative2")
            print("\n\n")
        }
    }

}

