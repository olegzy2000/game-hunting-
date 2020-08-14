import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataTable extends JFrame {
    public DataTable(ResultSet resultSet)
    {
        String[] columns = new String[] {
                "Id", "Name", "Amount games", "Record"
        };
        ArrayList<ArrayList<Object>>list=new ArrayList<ArrayList<Object>>();
            try {
                while (resultSet.next()){
                    list.add(new ArrayList<Object>());
                    list.get(list.size()-1).add(resultSet.getInt(1));
                    list.get(list.size()-1).add(resultSet.getString(2));
                    list.get(list.size()-1).add(resultSet.getInt(3));
                    list.get(list.size()-1).add(resultSet.getInt(4));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        Object[][] data = new Object[list.size()][list.get(0).size()];
            for(int i=0;i<list.size();i++){
                for(int j=0;j<list.get(i).size();j++){
                    data[i][j]=list.get(i).get(j);
                }
            }

        JTable table = new JTable(data, columns);
        table.setEnabled(false);

        this.add(new JScrollPane(table));

        this.setTitle("Table players");
        this.pack();
        this.setVisible(true);
    }

}
