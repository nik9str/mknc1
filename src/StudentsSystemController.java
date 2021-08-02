import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Controller for students system.
 * Responsible for controlling main processed for students system.
 * Part of Students system MVC
 * @see StudentsSystem
 * @see StudentsSystemView
 * */
public class StudentsSystemController {

    private State state = State.INITIATION;
    private StudentsSystemView view;
    private University university;
    private University universityForMerge;
    private Student selectedStudent;
    private Group selectedGroup;
    Scanner in = new Scanner(System.in);
    DAOUniversity daoUniversity;

    /**
     * Main constructor for StudentsSystemController
     * @see StudentsSystemView
     */
    public StudentsSystemController(StudentsSystemView view) {
        this.view = view;
        daoUniversity = new DAOUniversity();
    }

    /**
     * Main method for Students System that operates according to current state.
     */
    public void runStudentsSystem(){
        while(true) {
            switch (state) {
                case INITIATION: initialise(view); break;
                case MAIN_MENU: processMainMenu(view); break;
                case CREATE_STUDENT: processCreateStudent(view); break;
                case CREATE_GROUP:  processCreateGroup(view); break;
                case SHOW_ALL_STUDENTS: processShowAllStudents(view); break;
                case SHOW_ALL_GROUPS: processShowAllGroups(view); break;
                case FIND_STUDENT: processFindStudent(view); break;
                case EDIT_STUDENT: processEditStudent(view); break;
                case EDIT_STUDENTS_NAME: processEditStudentsName(view); break;
                case EDIT_STUDENTS_GROUP: processEditStudentsGroup(view); break;
                case FIND_GROUP: processFindGroup(view); break;
                case EDIT_GROUP: processEditGroup(view); break;
                case EDIT_GROUPS_NUMBER: processEditGroupsNumber(view); break;
                case EDIT_GROUPS_DEPARTMENT: processEditGroupsDepartment(view); break;
                case UNIVERSITIES_MERGE: processUniversitiesMerge(view); break;
                case EXIT: processExit(); break;
            }
        }
    }

    /**
     * Merges two university
     * */
    private void processUniversitiesMerge(StudentsSystemView view) {
        Boolean validUniversity = false;
        String newUniversity;
        while (!validUniversity) {
            view.showEnterUniversityName();
            university = daoUniversity.deSerializeUniversity(in.nextLine());
            if (university == null) {
                view.showEnterUniversityNotFound();
            } else {
                validUniversity = true;
            }
        }

        validUniversity = false;
        while (!validUniversity) {
            view.showEnterUniversityName();
            universityForMerge = daoUniversity.deSerializeUniversity(in.nextLine());
            if (universityForMerge == null) {
                view.showEnterUniversityNotFound();
            } else {
                validUniversity = true;
            }
        }
        view.showEnterUniversityName();
        newUniversity = in.nextLine();
        mergeUniversities(newUniversity, university, universityForMerge);
    }

    private void mergeUniversities(String newName, University university1, University university2){
        university1.getGroups().addAll(university2.getGroups());
        university1.getStudents().addAll(university2.getStudents());
        university = new University(newName, university1.getGroups(), university1.getStudents());
        state = State.MAIN_MENU;
    }

    /**
     * Method responsible for editing group department.
     */
    private void processEditGroupsDepartment(StudentsSystemView view) {
        view.showEnterGroupDepartment();
        String groupNumber = in.nextLine();
        selectedGroup.setDepartment(groupNumber);
        view.showGroup(selectedGroup);
        state = State.MAIN_MENU;
    }

    /**
     * Method responsible for editing group number.
     */
    private void processEditGroupsNumber(StudentsSystemView view) {
        view.showEnterGroupNumber();
        String groupNumber = in.nextLine();
        selectedGroup.setGroupNumber(groupNumber);
        view.showGroup(selectedGroup);
        state = State.MAIN_MENU;
    }

    /**
     * Method responsible for editing group.
     */
    private void processEditGroup(StudentsSystemView view) {
        view.showEditGroupOptions();
        switch (in.nextLine()){
            case "1" : state = State.EDIT_GROUPS_NUMBER; break;
            case "2" : state = State.EDIT_GROUPS_DEPARTMENT; break;
            case "3" : university.removeGroup(selectedGroup);
                       state = State.MAIN_MENU;
                       break;
            case "4" : state = State.MAIN_MENU; break;
            default: state = State.EDIT_GROUP; break;
        }
    }

    /**
     * Method responsible for finding group.
     */
    private void processFindGroup(StudentsSystemView view) {
        view.showEnterGroupNumber();
        String pattern = in.nextLine();
        List<Group> matchedGroups = university.getGroups(pattern);
        if (matchedGroups.size() > 1 ) {
            view.showGroups(matchedGroups);
            state = State.MAIN_MENU;
        }else if (matchedGroups.size() == 1){
            view.showGroups(matchedGroups);
            selectedGroup = matchedGroups.get(0);
            state = State.EDIT_GROUP;
        } else {
            view.showNoRecordsFound();
            state = State.MAIN_MENU;
        }
    }

    /**
     * Method responsible for editing group for student.
     */
    private void processEditStudentsGroup(StudentsSystemView view) {
        int option;
        while(true) {
            view.showChooseStudentGroup(university.getGroups());
            try {
                option = Integer.parseInt(in.nextLine());
                if (option>=0 && option <= university.getGroups().size()){
                    selectedStudent.setGroup(university.getGroups().get(option));
                    state = State.MAIN_MENU;
                    break;
                } else {
                    view.showReenteringMessage();
                }
            } catch (NumberFormatException e){
                view.showReenteringMessage();
            }
        }
    }

    /**
     * Method responsible for editing students name.
     */
    private void processEditStudentsName(StudentsSystemView view) {
        view.showEnterStudentFullName();
        String fullStudentName = in.nextLine();
        selectedStudent.setFullName(fullStudentName);
        view.showStudent(selectedStudent);
        state = State.MAIN_MENU;
    }

    /**
     * Method responsible for editing students.
     */
    private void processEditStudent(StudentsSystemView view) {
        view.showEditStudent();
        switch (in.nextLine()){
            case "1" : state = State.EDIT_STUDENTS_NAME; break;
            case "2" : state = State.EDIT_STUDENTS_GROUP; break;
            case "3" : university.removeStudent(selectedStudent);
                       state = State.MAIN_MENU; break;
            case "4" : state = State.MAIN_MENU; break;
            default: state = State.EDIT_STUDENT; break;
        }
    }

    /**
     * Method responsible for initialisation state processing.
     */
    private void initialise(StudentsSystemView view){
        view.showInitialisation();
        switch (in.nextLine()){
            case "1" : view.showEnterUniversityName();
                       university = new University(in.nextLine());
                       state =  State.MAIN_MENU;
                       break;
            case "2" : view.showEnterUniversityName();
                       university = daoUniversity.deSerializeUniversity(in.nextLine());
                       if (university == null){
                           view.showEnterUniversityNotFound();
                           break;
                       }
                       state = State.MAIN_MENU;
                       break;
            case "3" : state = State.UNIVERSITIES_MERGE; break;
            default: view.showReenteringMessage();
                     state = State.INITIATION;
                     break;
        }
    }

    /**
     * Method responsible for main menu state processing.
     */
    private void processMainMenu(StudentsSystemView view){
        view.showMainMenu();
        switch (in.nextLine()){
            case "1" : state = State.SHOW_ALL_STUDENTS; break;
            case "2" : state = State.SHOW_ALL_GROUPS; break;
            case "3" : state = State.CREATE_STUDENT; break;
            case "4" : state = State.CREATE_GROUP; break;
            case "5" : state = State.FIND_STUDENT; break;
            case "6" : state = State.FIND_GROUP; break;
            case "7" : state = State.EXIT; break;
            default: view.showReenteringMessage(); state = State.MAIN_MENU; break;
        }
    }

    /**
     * Method responsible for creating student.
     */
    private void processCreateStudent(StudentsSystemView view){
        String fullStudentName;
        Group group;
        int option;
        view.showEnterStudentFullName();
        fullStudentName = in.nextLine();
        while(true) {
            view.showChooseStudentGroup(university.getGroups());
            try {
                option = Integer.parseInt(in.nextLine());
                if (option>=0 && option <= university.getGroups().size()){
                    group = university.getGroups().get(option);
                    university.addStudent(new Student(fullStudentName, group, new Date()));
                    state = State.MAIN_MENU;
                    break;
                } else {
                    view.showReenteringMessage();
                }
            } catch (NumberFormatException e){
                view.showReenteringMessage();
            }
        }
        state = State.MAIN_MENU;
    }

    /**
     * Method responsible for creating group.
     */
    private void processCreateGroup(StudentsSystemView view){
        String groupNumber;
        String groupDepartment;
        view.showEnterGroupNumber();
        groupNumber = in.nextLine();
        view.showEnterGroupDepartment();
        groupDepartment = in.nextLine();
        university.addGroup(new Group(groupNumber,groupDepartment));
        state = State.MAIN_MENU;
    }

    /**
     * Method responsible for student searching.
     */
    private void processFindStudent(StudentsSystemView view){
        view.showEnterStudentFullName();
        String pattern = in.nextLine();
        List<Student> matchedStudents = university.getStudents(pattern);
        if (matchedStudents.size() > 1 ) {
            view.showStudents(matchedStudents);
            state = State.MAIN_MENU;
        }else if (matchedStudents.size() == 1){
            view.showStudents(matchedStudents);
            selectedStudent = matchedStudents.get(0);
            state = State.EDIT_STUDENT;
        } else {
            view.showNoRecordsFound();
            state = State.MAIN_MENU;
        }
    }

    /**
     * Method responsible for showing all groups.
     */
    private void processShowAllGroups(StudentsSystemView view){
        view.showGroups(university.getGroups());
        state = State.MAIN_MENU;
    }

    /**
     * Method responsible for showing all students.
     */
    private void processShowAllStudents(StudentsSystemView view){
        view.showStudents(university.getStudents());
        state = State.MAIN_MENU;
    }

    /**
     * Method responsible for saving current state and exit.
     */
    private void processExit(){
        daoUniversity.serializeUniversity(university);
        System.exit(0);
    }
}
