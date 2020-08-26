import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentStorageDB {
    Connection connection;
    {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/students","postgres","password");
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public void save(Student student){
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("insert into phone_numbers values ( default, ?) returning id");
            preparedStatement.setInt(1, student.getPhone().getPhoneNumber());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int phoneId = resultSet.getInt(1);
            PreparedStatement preparedStatement2 = connection.prepareStatement("insert into students values ( default, ?, ?, ?, ?)");
            preparedStatement2.setString(1, student.getName());
            preparedStatement2.setString(2, student.getSurname());
            preparedStatement2.setLong(3, student.getGroup().getId());
            preparedStatement2.setLong(4, phoneId);
            preparedStatement2.execute();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException throwable) {
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            throwable.printStackTrace();
        }
    }

    public List<Student> getAll(){
        List<Student> students = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from students s join phone_numbers ph on s.phone_id = ph.id join groups g on s.group_id = g.id");
            while (resultSet.next()) {
                long id = resultSet.getLong(1);
                String name = resultSet.getString(2);
                String surname = resultSet.getString(3);
                Phone phone = new Phone(resultSet.getLong(6), resultSet.getInt(7));
                Group group = new Group(resultSet.getLong(8), resultSet.getString(9), resultSet.getString(10));
                Student student = new Student(id, name, surname, group, phone);
                students.add(student);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return students;
    }
    public Student getByID(long id){
        Student student = null;
        for (int i = 0; i < getAll().size() ; i++) {
            if (id == getAll().get(i).getId()){
                student = getAll().get(i);
            }
        }
        return student;
    }

    public Student getBySurname(String surname){
        Student student = null;
        for (int i = 0; i < getAll().size() ; i++) {
            if (surname.equals(getAll().get(i).getSurname())){
                student = getAll().get(i);
            }
        }
        return student;
    }

    public String updateName(String newName, long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE students SET name = ? WHERE id = ?");
            preparedStatement.setString(1, newName);
            preparedStatement.setLong(2, id);
            preparedStatement.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return newName;
    }

    public int updatePhone(int number, long id){
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("select * from students where id = ?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            long id1 = resultSet.getLong(1);
            String name = resultSet.getString(2);
            String surname = resultSet.getString(3);
            Phone phone = new Phone(resultSet.getLong(5), 0);
            Student student = new Student(id1, name, surname, null, phone);

            PreparedStatement preparedStatement1 = connection.prepareStatement("update phone_numbers set number = ? where id = ?");
            preparedStatement1.setLong(1, number);
            preparedStatement1.setLong(2, student.getPhone().getId());
            preparedStatement1.execute();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException throwable) {
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            throwable.printStackTrace();
        }
        return number;
    }

    public void removeStudentByID(long id){
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement0 = connection.prepareStatement("select * from students s join phone_numbers ph on s.phone_id = ph.id where s.id = ?");
            preparedStatement0.setLong(1, id);
            ResultSet resultSet = preparedStatement0.executeQuery();
            resultSet.next();
            long id1 = resultSet.getLong(1);
            String name = resultSet.getString(2);
            String surname = resultSet.getString(3);
            Phone phone = new Phone(resultSet.getLong(6), resultSet.getInt(7));
            Student student = new Student(id, name, surname, null, phone);

            PreparedStatement preparedStatement1 = connection.prepareStatement("delete from phone_numbers where id = ?");
            preparedStatement1.setLong(1, student.getPhone().getId());
            preparedStatement1.execute();
            PreparedStatement preparedStatement = connection.prepareStatement("delete from students where id = ?");
            preparedStatement.setLong(1, id1);
            preparedStatement.execute();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException throwable) {
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            throwable.printStackTrace();
        }
    }

    public static void main(String[] args) {
        StudentStorageDB studentStorageDB = new StudentStorageDB();
//        studentStorageDB.save(new Student("test2", "surname", new Group(2, null,null), new Phone(5556768)));
//        studentStorageDB.removeStudentByID(4);
        studentStorageDB.updatePhone(33333333, 1);
    }
 }
