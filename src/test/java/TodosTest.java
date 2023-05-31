import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TodosTest {
    SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");
    String[] subtasks = { "Молоко", "Яйца", "Хлеб" };
    Epic epic = new Epic(55, subtasks);
    Meeting meeting = new Meeting(
            555,
            "Выкатка 3й версии приложения",
            "Приложение НетоБанка",
            "Во вторник после обеда"
    );
    Todos todos = new Todos();

    @Test
    public void shouldAddThreeTasksOfDifferentType() {

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = { simpleTask, epic, meeting };
        Task[] actual = todos.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindInSimpleTaskIfTrue() {

        boolean expected = true;
        boolean actual = simpleTask.matches("Позвонить");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldFindInSimpleTaskIfFalse() {

        boolean expected = false;
        boolean actual = simpleTask.matches("Приготовить");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldFindInEpicIfTrueFirst() {

        boolean expected = true;
        boolean actual = epic.matches("Молоко");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldFindInEpicIfTrueSecond() {

        boolean expected = true;
        boolean actual = epic.matches("Яйца");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldFindInEpicIfTrueThird() {

        boolean expected = true;
        boolean actual = epic.matches("Хлеб");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldFindInEpicIfFalse() {

        boolean expected = false;
        boolean actual = epic.matches("Квас");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldFindInMeetingTopicIfTrue() {

        boolean expected = true;
        boolean actual = meeting.matches("Выкатка");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldFindInMeetingProjectIfTrue() {

        boolean expected = true;
        boolean actual = meeting.matches("Приложение");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldFindInMeetingTopicIfFalse() {

        boolean expected = false;
        boolean actual = meeting.matches("Запуск");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldFindInMeetingProjectIfFalse() {

        boolean expected = false;
        boolean actual = meeting.matches("Сайт");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldSearch() {

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {epic};
        Task[] actual = todos.search("Молоко");
        Assertions.assertArrayEquals(expected, actual);
    }

}
