import java.io.Serializable;
import java.util.Date;

/**
 * Student: Full name, group and enrollment date.
 */
public class Student implements Serializable {
    private String fullName;
    private Group group;
    private Date enrollmentDate;

    /**
     * Student main constructor requires full name, group and enrollment date.
     */
    public Student(String fullName, Group group, Date enrollmentDate) {
        this.fullName = fullName;
        this.group = group;
        this.enrollmentDate = enrollmentDate;
    }

    /**
     * Student full name setter.
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Student group setter.
     */
    public void setGroup(Group group) {
        this.group = group;
    }

    /**
     * Student full name getter.
     * @return Students full name
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Student group getter.
     * @return Students group
     * @see Group
     */
    public Group getGroup() {
        return group;
    }

    /**
     * Student enrollment date getter.
     * @return Students enrollment date
     */
    public Date getEnrollmentDate() {
        return enrollmentDate;
    }
}
