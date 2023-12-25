package jju.soft;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

class loginBuff {
    private JFrame Loginframe;
    private JTextField userName;
    private JPasswordField userPwd;

    /*
     *一个带有JFrame的构造方法
     */

    public loginBuff(JFrame Loginframe){
        this.Loginframe = Loginframe;
    }

    /*
     *设计框架的基本信息
     */
    protected void frameBuff(){

        Loginframe.setBounds(450,150,500,500);
        Loginframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Loginframe.setLayout(new GridLayout(8,1));

    }

    /*
     *菜单条 （分为两部分）：
     * 1. 关于我们，我的一些个人信息，以及我的个人网站，Github
     * 2. 支持我们，向我们捐款
     *
     */
    protected void menuBuff(){

    }

    protected void infoBuff(){

        /*
         *  获取用户登入信息的面板
         */
        JPanel Name = new JPanel();
        JPanel Pwd = new JPanel();

        JLabel name = new JLabel("用    户    名：");
        userName = new JTextField(20);
        Name.add(name);
        Name.add(userName);

        JLabel pwd = new JLabel("密           码：");
        userPwd = new JPasswordField(20);
        Pwd.add(pwd);
        Pwd.add(userPwd);

        Loginframe.add(new Panel());
        Loginframe.add(new Panel());
        Loginframe.add(Name);
        Loginframe.add(Pwd);
        Loginframe.add(new Panel());
    }
    /*
     * 创建两个按钮（登入 和 注册）
     * 并进行监视
     *
     */
    protected void buttonBuff() {

        JButton loginButton = new JButton("登入");

        loginButton.setBackground(new Color(212, 212, 212));

        JPanel loginPanel = new JPanel(new GridLayout(1, 5));

        loginPanel.add(new JPanel());
        loginPanel.add(new JPanel());
        loginPanel.add(loginButton);
        loginPanel.add(new JPanel());
        loginPanel.add(new JPanel());
        Loginframe.add(loginPanel);
        Loginframe.add(new Panel());

        //对登入事件进行监视
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String un = userName.getText();
                String up = new String(userPwd.getPassword());

                userData userdata = new userData();
                userdata.setUserName(un);

                userObjectIO io = new userObjectIO();//userdata
                userData ud;

                try {
                    ud = io.ObjectInputStream_(un);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                if (ud == null){
                    JOptionPane.showMessageDialog(Loginframe,"用户不存在！","用户名错误",JOptionPane.WARNING_MESSAGE);
                }else if (!ud.ensurePwd(up)){
                    JOptionPane.showMessageDialog(Loginframe,"密码错误！","密码错误",JOptionPane.WARNING_MESSAGE);
                } else {
                    Loginframe.dispose();

                    try {
                        new goodsManagement();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    } catch (ClassNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }

                }
            }
        });



        //注册按钮
        JButton registerButton = new JButton("注册");
        registerButton.setBackground(new Color(212, 212, 212));

        JPanel registerPanel = new JPanel(new GridLayout(1,5));
        registerPanel.add(registerButton);
        registerPanel.add(new Panel());
        registerPanel.add(new Panel());
        registerPanel.add(new Panel());
        registerPanel.add(new Panel());
        Loginframe.add(registerPanel);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterFrame registerFrame = new RegisterFrame();
                registerFrame.action();
            }
        });
    }
}

public class LoginFrame {
    public void login(){
        JFrame Loginframe = new JFrame("超市商品管理系统");

        loginBuff buff = new loginBuff(Loginframe);

        buff.menuBuff();
        buff.frameBuff();
        buff.infoBuff();
        buff.buttonBuff();

        Loginframe.setVisible(true);

    }
}
