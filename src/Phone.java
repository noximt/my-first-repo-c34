import java.util.Objects;

public class Phone {
    private long id;
    private int phoneNumber;

    public Phone() {
    }

    public Phone(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Phone(long id, int phoneNumber) {
        this.id = id;
        this.phoneNumber = phoneNumber;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phone phone = (Phone) o;
        return phoneNumber == phone.phoneNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneNumber);
    }

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", phoneNumber=" + phoneNumber +
                '}';
    }
}
