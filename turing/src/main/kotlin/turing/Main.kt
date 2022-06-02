package turing
import java.io.IOException

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        try {
            println("Введите разделитель: ")
            val splitter = readln()
            val tur = TuringMachine(splitter)
            tur.loadRules("src/main/resources/input.txt")
            tur.simulation()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}
