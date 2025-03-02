package UI;

import bean.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LoginUI extends JFrame implements ActionListener {
    private JTextField loginNameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;
    //定义一个静态的集合，存储系统中全部的用户信息
    private static ArrayList<User> allUsers=new ArrayList<>();
    //初始化几个测试的用户信息，作为登录用
    static{
        allUsers.add(new User("张三丰","zsf123456","taiji"));
        allUsers.add(new User("张无忌","28","qiankun"));
        allUsers.add(new User("周杰伦","hhhhhh","jaychou"));
    }
    public LoginUI(){
        super("登录界面");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(400,400);
        this.setLocationRelativeTo(null);
        createAndShowGUI();
    }
    private void createAndShowGUI(){
        //创建主界面
        //创建面板
        JPanel panel=new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(240,240,240));
        //设置字体和颜色
        Font custumFont=new Font("楷体",Font.BOLD,18);
        Color primaryColor=new Color(66,135,254);
        Color secondColor=new Color(204,204,204);
        //标题
        JLabel titleLabel=new JLabel("人事管理系统");
        titleLabel.setBounds(50,30,300,30);
        titleLabel.setFont(new Font("楷体",Font.BOLD,24));
        panel.add(titleLabel);
        //用户名标签
        JLabel usernameLabel=new JLabel("用户名");
        usernameLabel.setBounds(50,100,150,30);
        usernameLabel.setFont(custumFont);
        panel.add(usernameLabel);
        //用户名输入框
        loginNameField =new JTextField();
        loginNameField.setBounds(160,100,190,30);
        loginNameField.setFont(custumFont);
        panel.add(loginNameField);
        //密码标签
        JLabel passwordLabel=new JLabel("密   码");
        passwordLabel.setBounds(50,150,150,30);
        passwordLabel.setFont(custumFont);
        panel.add(passwordLabel);
        //密码输入框
        passwordField =new JPasswordField();
        passwordField.setBounds(160,150,190,30);
        passwordField.setFont(custumFont);
        passwordField.setEchoChar('*');
        panel.add(passwordField);
        //登录按钮
        loginButton=new JButton("登  录");
        loginButton.setBounds(50,200,150,30);
        loginButton.setFont(custumFont);
        loginButton.setBackground(primaryColor);
        loginButton.setForeground(Color.WHITE);
        panel.add(loginButton);
        loginButton.addActionListener(this);
        // 注册按钮
        registerButton=new JButton("注  册");
        registerButton.setBounds(200,200,150,30);
        registerButton.setFont(custumFont);
        registerButton.setBackground(secondColor);
        registerButton.setForeground(Color.BLACK);
        panel.add(registerButton);
        registerButton.addActionListener(this);
        //添加面板到窗口
        this.add(panel);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //既可能是登录按钮，也可能是注册按钮
        //得判断登录按钮还是注册按钮
        JButton btn=(JButton) e.getSource();
        if(btn==loginButton){
            login();
        }else{
            System.out.println("注册开始");
        }
    }
    private void login() {
        //获取用户的用户名和密码
        String loginName = loginNameField.getText();
        String password = new String(passwordField.getPassword());
        User user = getUserByLoginName(loginName);
        if (user != null) {
            if (user.getPassword().equals(password)) {
                System.out.println("登录成功");
                new EmployeeManagerUI(user.getUsername());
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "密码错误");
            }
        } else {
            JOptionPane.showMessageDialog(this,"登录名称不存在");
        }
    }
    //根据登录名称去查询，返回
    private User getUserByLoginName(String loginName){
        for(int i=0;i<allUsers.size();i++){
                User user=allUsers.get(i);
                if(user.getLoginName().equals(loginName)){
                    return user;
                }
         }
            return null;
        }
    }


