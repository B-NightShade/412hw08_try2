import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Controller {
    static ArrayList<String> arrayList = new ArrayList<String>();
    static View v1 = new View();
    static Model m1 = new Model();
    static String name;
    static int age;
    static int id;

    public static void main(String[] args) {
        m1.createTable();
        v1.go();
    }

    static class tabListener implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent e) {
            try {
                ResultSet rs = m1.selectconnection();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    int age = rs.getInt("age");
                    String s = String.format("%3d %10s %3d", id, name, age);
                    arrayList.add(s);
                }
                m1.conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            v1.listModel.clear();
            for (String s : Controller.arrayList) {
                v1.listModel.addElement(s);
            }
            arrayList.clear();
        }
    }

    static class createListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            name = v1.textFieldName.getText();
            try {
                age = Integer.parseInt(v1.textFieldAge.getText());
            }
            catch (NumberFormatException i){
                i.printStackTrace();
            }
            m1.createConnection();
        }
    }

    static class updateListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if(e.getValueIsAdjusting() == false){
                int index = v1.jList2.getSelectedIndex();
                if (index != -1){
                    String s = v1.listModel.elementAt(index);
                    String[] words = s.trim().split("\\s+");
                    try {
                        id = Integer.parseInt(words[0]);
                    }
                    catch(NumberFormatException i){
                        i.printStackTrace();
                    }
                    String name = words[1];
                    try {
                        age = Integer.parseInt(words[2]);
                    }
                    catch(NumberFormatException j){
                        j.printStackTrace();
                    }
                    catch(ArrayIndexOutOfBoundsException b){
                        b.printStackTrace();
                    }
                    v1.textFieldName2.setText(name);
                    v1.textFieldAge2.setText(String.valueOf(age));
                    v1.jButton2.setEnabled(true);
                    v1.jButton2.addActionListener(new UpdateButton());
                }
            }
        }
    }

    static class UpdateButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if (id != -1) {
                name = v1.textFieldName2.getText();
                try {
                    age = Integer.parseInt(v1.textFieldAge2.getText());
                }
                catch(NumberFormatException i){
                    i.printStackTrace();
                }
                m1.updateConnection();
            }
        }
    }

    static class DeleteAction implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            id = 0;
            String[] words;
            int i = v1.jList.getSelectedIndex();
            String s = v1.jList.getSelectedValue();
            try {
                words = s.trim().split(" ");
                id = Integer.parseInt(words[0]);
            }
            catch(NullPointerException k){
                k.printStackTrace();
            }
            catch(NumberFormatException j){
                j.printStackTrace();
            }
            try {
                v1.listModel.remove(i);
            }
            catch(ArrayIndexOutOfBoundsException c){
                c.printStackTrace();
            }
            m1.delConnection();
        }
    }
}

