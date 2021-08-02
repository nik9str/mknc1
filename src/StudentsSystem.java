public class StudentsSystem {
    public static void main(String[] args) {
        StudentsSystemView studentsSystemView = new StudentsSystemView();
        StudentsSystemController studentsSystemController = new StudentsSystemController(studentsSystemView);
        studentsSystemController.runStudentsSystem();
    }
}
