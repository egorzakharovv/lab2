import data.Student
import data.studentList
import kotlinx.html.*
import kotlinx.html.dom.append
import kotlinx.html.js.h1
import kotlinx.html.js.li
import kotlinx.html.js.ol
import kotlinx.html.js.onClickFunction
import org.w3c.dom.events.Event
import kotlin.browser.document
import kotlin.dom.clear


var ascending = true


fun main() {
    document.getElementById("root")!!
        .append {
            h1 {
                +"Список студентов группы 28-м:"
                onClickFunction = onCLickFunction()
            }
            ol {
                attributes += "id" to "listStudents"
                studentList.map {
                    li {
                        +"${it.firstname} ${it.surname}"
                        attributes += "id" to it.surname
                        attributes += "class" to "color"
                        onClickFunction = colors(it)
                    }
                }

            }
        }
}

private fun onCLickFunction(): (Event) -> Unit {
    return {
        val listStudents = document.getElementById("listStudents")!!
        listStudents.clear()
        listStudents.append {
            if (ascending)
                studentList.sortBy { it.firstname }
            else
                studentList.sortByDescending { it.firstname }
                ascending = !ascending
                 studentList.map {
                      li {
                           +"${it.firstname} ${it.surname}"
                }
            }
        }
    }
}

private fun colors(it:Student): (Event) -> Unit {
    return {
            _ ->
        val id = document.getElementById(it.surname)!!
        id.className = "newcolor"
    }
}