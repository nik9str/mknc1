import java.io.Serializable;
import java.util.List;

/**
 * Student: group number(name) and department.
 */
public class Group implements Serializable {
    private String groupNumber;
    private String department;
    private static List<Group> groups;

    /**
     * Main group constructor requires group number and department.
     */
    public Group(String groupNumber, String department) {
        this.groupNumber = groupNumber;
        this.department = department;
    }

    /**
     * Group number setter.
     */
    public void setGroupNumber(String groupNumber) {
        this.groupNumber = groupNumber;
    }

    /**
     * Group department setter.
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * Group number getter.
     * @return Group number
     */
    public String getGroupNumber() {
        return groupNumber;
    }

    /**
     * Group department getter.
     * @return Group department
     */
    public String getDepartment() {
        return department;
    }
}
