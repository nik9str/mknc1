import java.util.List;


/**
 * View for students system.
 * Responsible for console output.
 * Part of Students system MVC
 * @see StudentsSystemController
 * @see StudentsSystem
 */
public class StudentsSystemView {

    /**
     * Shows information related to provided student.
     * Information shown: Full name, Group and Enrollment date.
     * @see Student
     */
    public void showStudent(Student student){
        System.out.println("Full name: " + student.getFullName());
        System.out.print("Group: ");
        this.showGroup(student.getGroup());
        System.out.println("Enrollment date: " + student.getEnrollmentDate());
    }

    /**
     * Shows information related to provided students list.
     * Information shown for each student: Full name, Group and Enrollment date.
     * @see Student
     */
    public void showStudents(List<Student> students){
        for(Student student : students) {
            this.showStudent(student);
        }
    }

    /**
     * Shows initialisation state options.
     */
    public void showInitialisation(){
        System.out.println("Please choose action:");
        System.out.println("1. Create new university");
        System.out.println("2. Open existing university");
        System.out.println("3. Merge universities");
    }

    /**
     * Shows main menu state options.
     */
    public void showMainMenu(){
        System.out.println("Please choose action:");
        System.out.println("1. Show all students");
        System.out.println("2. Show all groups");
        System.out.println("3. Create student");
        System.out.println("4. Create group");
        System.out.println("5. Find student");
        System.out.println("6. Find group");
        System.out.println("7. Exit");
    }

    /**
     * Shows request for student full name.
     * @see Student
     */
    public void showEnterStudentFullName(){
        System.out.println("Please enter student full name: ");
    }

    /**
     * Shows request for student group number.
     * @see Group
     */
    public void showEnterGroupNumber(){
        System.out.println("Please enter group number: ");
    }

    /**
     * Shows request for student group department.
     * @see Group
     */
    public void showEnterGroupDepartment(){
        System.out.println("Please enter group department: ");
    }

    /**
     * Shows request for university name.
     * @see University
     */
    public void showEnterUniversityName(){
        System.out.println("Please enter university name: ");
    }

    /**
     * Shows request for university name.
     * @see University
     */
    public void showEnterUniversityNotFound(){
        System.out.println("University not found");
    }

    /**
     * Shows information related to provided groups list.
     * Information shown for each group: Department and Group number.
     * @see Group
     */
    public void showGroups(List<Group> groups){
        for (int i = 0; i < groups.size(); i++) {
            System.out.print(i + " ");
            this.showGroup(groups.get(i));
        }
    }

    /**
     * Shows information related to provided groups.
     * Information shown for group: Department and Group number.
     * @see Group
     */
    public void showGroup(Group group){
        System.out.println("Department: " + group.getDepartment() + " Group: " + group.getGroupNumber());
    }

    /**
     * Shows request to choose from possible options.
     */
    public void showReenteringMessage(){
        System.out.println("Please choose option from provided list");
    }

    /**
     * Shows request to choose from possible group options.
     * @see Group
     */
    public void showChooseStudentGroup(List<Group> groups){
        System.out.println("Please choose group: ");
        this.showGroups(groups);
    }

    /**
     * Shows no records found message.
     */
    public void showNoRecordsFound(){
        System.out.println("No records found");
    }

    /**
     * Shows options possible for editing student.
     * @see Student
     */
    public void showEditStudent() {
        System.out.println("Please choose action:");
        System.out.println("1. Edit students name");
        System.out.println("2. Edit students group");
        System.out.println("3. Delete student");
        System.out.println("4. Exit");
    }

    /**
     * Shows options possible for editing group.
     * @see Group
     */
    public void showEditGroupOptions() {
        System.out.println("Please choose action:");
        System.out.println("1. Edit group number");
        System.out.println("2. Edit group department");
        System.out.println("3. Delete group");
        System.out.println("4. Exit");
    }
}
