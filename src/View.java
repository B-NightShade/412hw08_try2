import javax.swing.*;
import java.awt.*;


public class View {
    public DefaultListModel<String> listModel;
    public JTextField textFieldName;
    public JTextField textFieldAge;
    public JTextField textFieldName2;
    public JTextField textFieldAge2;
    public JList<String> jList;
    public JList<String> jList2;
    public JButton jButton;
    public JButton jButton2;

    protected void go() {
        listModel = new DefaultListModel<String>();
        JFrame jFrame = new JFrame();
        JTabbedPane jTabs = new JTabbedPane();
        jTabs.add("CREATE", makeCreateTab());
        jTabs.add("READ", makeReadTab());
        jTabs.add("UPDATE", makeUpdateTab());
        jTabs.add("DELETE", makeDeleteTab());

        jTabs.addChangeListener(new Controller.tabListener());
        jFrame.add(jTabs);
        jFrame.setSize(500, 500);
        jFrame.setVisible(true);
    }

    private JPanel makeCreateTab() {
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(3, 2));
        jPanel.add(new JLabel("NAME:"));
        textFieldName = new JTextField(10);
        textFieldAge = new JTextField(10);
        jPanel.add(textFieldName);
        jPanel.add(new JLabel("AGE:"));
        jPanel.add(textFieldAge);
        jButton = new JButton("CREATE");
        jButton.addActionListener(new Controller.createListener());

        jPanel.add(jButton);
        return jPanel;
    }

    private JPanel makeReadTab(){
        JPanel jPanel = new JPanel();
        jList = new JList(listModel);
        jPanel.add(jList);
        return jPanel;
    }

    private JPanel makeUpdateTab() {
        JPanel jPanel = new JPanel();
        jList2 = new JList(listModel);
        jList2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        jPanel.add(jList2);

        JPanel inner = new JPanel();
        inner.setLayout(new GridLayout(2,2));
        inner.add(new JLabel("NAME:"));
        textFieldName2= new JTextField(10);
        textFieldAge2= new JTextField(10);
        inner.add(textFieldName2);
        inner.add(new JLabel("AGE:"));
        inner.add(textFieldAge2);

        jPanel.add(inner);

        jButton2 = new JButton("UPDATE");
        jButton2.setEnabled(false);
        jList2.addListSelectionListener(new Controller.updateListener());
        jPanel.add(jButton2);
        return jPanel;
    }
    private JPanel makeDeleteTab(){
        JPanel jPanel = new JPanel();
        jList = new JList(listModel);
        jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jPanel.add(jList);
        jButton = new JButton("DELETE");
        jButton.addActionListener(new Controller.DeleteAction());
        jPanel.add(jButton);
        return jPanel;
    }

}
