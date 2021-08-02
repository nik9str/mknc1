import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * University: container for groups and students.
 */
public class University implements Serializable {
    private String name;
    private List<Group> groups = new ArrayList<Group>();

    public String getName() {
        return name;
    }

    private List<Student> students = new ArrayList<Student>();

    public University(String name) {
        this.name = name;
    }

    public University(String name, List<Group> groups, List<Student> students) {
        this.name = name;
        this.groups = groups;
        this.students = students;
    }

    /**
     * Groups getter
     * @return list of groups
     * @see Group
     * */
    public List<Group> getGroups() {
        return groups;
    }

    /**
     * Students getter
     * @return list of students
     * @see Student
     * */
    public List<Student> getStudents() {
        return students;
    }

    /**
     * Method to add group to groups list
     * */
    public void addGroup(Group group) {
        this.groups.add(group);
    }

    /**
     * Method to add student to students list
     * */
    public void addStudent(Student student) {
        this.students.add(student);
    }

    /**
     * Method to get students according to search condition.
     * Condition supports patterns * and ?
     * */
    public List<Student> getStudents(String searchCondition) {
        List<Student> matchedStudents = new ArrayList<Student>();
        searchCondition = searchCondition.replace("*", ".*");
        searchCondition = searchCondition.replace("?", ".");
        for (Student student : students) {
            if (Pattern.matches(searchCondition, student.getFullName())) {
                matchedStudents.add(student);
            }
        }
        return matchedStudents;
    }

    /**
     * Method to get groups according to search condition.
     * Condition supports patterns * and ?
     * */
    public List<Group> getGroups(String searchCondition) {
        List<Group> matchedGroups = new ArrayList<Group>();
        searchCondition = searchCondition.replace("*", ".*");
        searchCondition = searchCondition.replace("?", ".");
        for (Group group : groups) {
            if (Pattern.matches(searchCondition, group.getGroupNumber())) {
                matchedGroups.add(group);
            }
        }
        return matchedGroups;
    }

    /**
     * Method to remove group from groups list
     * */
    public void removeGroup(Group group) {
        groups.remove(group);
    }

    /**
     * Method to remove student from students list
     * */
    public void removeStudent(Student student) {
        students.remove(student);
    }

}
