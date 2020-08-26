import java.util.Objects;

public class Group {
    private long id;
    private String number;
    private String faculty;

    public Group() {
    }

    public Group(long id, String number, String faculty) {
        this.id = id;
        this.number = number;
        this.faculty = faculty;
    }

    public Group(String number, String faculty) {
        this.number = number;
        this.faculty = faculty;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return Objects.equals(number, group.number) &&
                Objects.equals(faculty, group.faculty);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, faculty);
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", faculty='" + faculty + '\'' +
                '}';
    }
}
