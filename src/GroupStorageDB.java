import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GroupStorageDB {
    Connection connection;
    {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/students","postgres","password");
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public void save(Group group){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into groups values (default, ?, ?)");
            preparedStatement.setString(1, group.getNumber());
            preparedStatement.setString(2, group.getFaculty());
            preparedStatement.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public List<Group> getAll(){
                List<Group> groups = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from groups");
            while (resultSet.next()) {
                long id = resultSet.getLong(1);
                String number = resultSet.getString(2);
                String faculty = resultSet.getString(3);
                Group group1 = new Group(id, number, faculty);
                groups.add(group1);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return groups;
    }
}
