package UI;

import bean.Employee;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Date;

import static jdk.internal.misc.OSEnvironment.initialize;

public class EmployeeManagerUI extends JFrame {
    private JFrame frame;
    private JTable table;  //表格
    private DefaultTableModel model;  //表格模型
    private JTextField nameTextFieldSearch;  //搜索输入框

    //准备一个静态集合，放置所有员工
    private static ArrayList<Employee> employees=new ArrayList<>();

    public EmployeeManagerUI(){
    }
    public EmployeeManagerUI(String username){
        super("欢迎"+username+"进入员工管理界面");
        frame=this;
        initialize();
        this.setVisible(true);//显示窗口
    }

    private void initialize(){
        this.setBounds(100,100,800,600);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.getContentPane().setLayout(new BorderLayout());

 
        //输入框与搜索按钮
        JPanel topPanel=new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        nameTextFieldSearch=new JTextField(20);
        JButton btnSearch=new JButton("搜索");
        JButton btnAdd=new JButton("添加");
        topPanel.add(nameTextFieldSearch);
        topPanel.add(btnSearch);
        topPanel.add(btnAdd);

        //创建表格模型
        model =new DefaultTableModel(
                new Object[][]{},
                new String[]{"ID","姓名","性别","年龄","电话","职位","入职日期","薪水","部门"}
        ){
            public boolean isCellEditable(int row,int column){return false;
            }
        };
        table=new JTable(model);
        JScrollPane scrollPane =new JScrollPane(table);
        table.setRowHeight(30);

        //添加数据表格
        for(int i=0;i<20; i++){
            model.addRow(new Object[]{i+1,"员工"+(i+1),"男","18853668016","java工程师",new Date().toLocaleString(),30000,"部门"+(i+1)});
        }
        //右键菜单

        JPopupMenu popupMenu=new JPopupMenu();
        JMenuItem editItem=new JMenuItem("编辑");
        JMenuItem deleteItem=new JMenuItem("删除");
        popupMenu.add(editItem);
        popupMenu.add(deleteItem);
        table.addMouseListener((new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getButton()==MouseEvent.BUTTON3){
                    int row=table.rowAtPoint(e.getPoint());
                    if(row>=0){
                        table.setRowSelectionInterval(row,row);
                        popupMenu.show(table,e.getX(),e.getY());
                    }
                }
            }


        }));
        //绑定事件到菜单项
        editItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow= table.getSelectedRow();
                if(selectedRow>=0){
                    int id=(Integer)model.getValueAt(selectedRow,0);
                    JOptionPane.showMessageDialog(frame,"编辑ID"+id);
                }
            }
        });
        deleteItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow=table.getSelectedRow();
                if(selectedRow>=0){
                    int id=(Integer)model.getValueAt(selectedRow,0);
                    JOptionPane.showMessageDialog(frame,"删除ID"+id);
                }
            }
        });
        //搜索按钮监听器
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchValue=nameTextFieldSearch.getText();
            }
        });
        //添加监听器按钮
        btnAdd.addActionListener(e ->{
            //添加员工信息输入的界面
            //new AddEmployeeUI();
        });

        frame.getContentPane().add(topPanel,BorderLayout.NORTH);
        frame.getContentPane().add(scrollPane,BorderLayout.NORTH);
    }

}
