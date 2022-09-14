import java.lang.Integer.min
import kotlin.math.max

fun main(args: Array<String>) {
    val browserHistory = BrowserHistory("www.google.com")
    while(true) {
        println("Select from the following options")
        println("1. Show history\n2. Visit\n3. Move Forward\n4. Move Backward\n5. Exit")
        when(readln().toInt()){
            1 -> browserHistory.printHistory()
            2 -> {
                println("Enter the url")
                val url = readln()
                browserHistory.visit(url)
            }
            3 -> {
                println("Enter number of steps to move forward")
                val steps = readln().toInt()
                println(browserHistory.moveForward(steps))
            }
            4 -> {
                println("Enter number of steps to move backward")
                val steps = readln().toInt()
                println(browserHistory.moveBackward(steps))
            }
            5 -> break;
        }
    }
}

class BrowserHistory(private val homepage: String) {
    private val history = arrayListOf<String>(homepage)
    private var currentPage = 0;
    private var maxForward = 0;
    fun visit(url: String){
        if(currentPage == maxForward) {
            if(history.size == currentPage + 1)history.add(url)
            else history[currentPage +1] = url
            ++currentPage
            ++maxForward
        } else {
            maxForward = currentPage + 1
            currentPage = maxForward
            history[currentPage] = url
        }
    }
    fun moveForward(steps: Int): String {
        currentPage = min(maxForward, currentPage + steps)
        return history[currentPage];
    }
    fun moveBackward(steps: Int): String {
        currentPage = max(0, currentPage - steps)
        return history[currentPage]
    }
    fun printHistory(){
        for(i in 0..maxForward)
            println(history[i])
    }
}