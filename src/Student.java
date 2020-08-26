import java.util.Objects;

public class Student {
    private long id;
    private String name;
    private String surname;
    private Group group;
    private Phone phone;

    public Student() {
    }

    public Student(String name, String surname, Group group, Phone phone) {
        this.name = name;
        this.surname = surname;
        this.group = group;
        this.phone = phone;
    }

    public Student(long id, String name, String surname, Group group, Phone phone) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.group = group;
        this.phone = phone;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(name, student.name) &&
                Objects.equals(surname, student.surname) &&
                Objects.equals(group, student.group) &&
                Objects.equals(phone, student.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, group, phone);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", group=" + group +
                ", phone=" + phone +
                '}';
    }
}