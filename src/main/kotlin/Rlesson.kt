import data.Student
import data.Lesson
import data.studentList
import org.w3c.dom.events.Event
import react.*
import react.dom.ol

interface RLessonList : RProps {
    var subject: Array<Lesson>
    var listStudent :Array<Student>
    var present: Array<Boolean>
}

interface RSubjectState : RState {
    var present: Array<Boolean>
}
class RLesson : RComponent<RLessonList, RSubjectState>() {
    override fun componentWillMount() {
        state.apply {
        present = Array(props.listStudent.size) { false }
        }
    }

   fun RBuilder.onIndex(): (Int) -> (Event) -> Unit = {
        onClick(it)
    }
    override fun RBuilder.render() {
        props.subject.map {
            + it.name
            ol {
                rstudentlist(props.listStudent, state.present, onIndex())
            }
        }
    }

    fun RBuilder.onClick(index: Int): (Event) -> Unit = {
      setState {
         present[index] = !present[index]
        }
    }
}

    fun RBuilder.rlesson(subject:  ArrayList<Lesson> ) =
    child( RLesson::class)
    {
        attrs.subject = subject.toTypedArray()
        attrs.listStudent = studentList.toTypedArray()
    }
