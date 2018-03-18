package decryptPwd;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * 简单的加密解密功能
 * @author: 潘峰
 * @date: 18/03/2018 9:15 PM
 */
public class App extends JFrame {
    private static JTextField pwdText;
    private static JTextField passwordText;
    private static JTextField userText;

    /**
     * {
     * 创建并显示GUI。出于线程安全的考虑，
     * 这个方法在事件调用线程中调用。
     */
    private static void createAndShowGUI() {
        // 确保一个漂亮的外观风格
        JFrame.setDefaultLookAndFeelDecorated(true);

        // 创建及设置窗口
        JFrame frame = new JFrame("新农险加密工具");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        // 添加 "Hello World" 标签
//        JLabel label = new JLabel("Hello World");
//        frame.getContentPane().add(label);

        JPanel panel = new JPanel();

        placeComponents(panel);
        // 添加面板
        frame.add(panel);

        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        // 显示窗口
        //frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // 显示应用 GUI
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void placeComponents(JPanel panel) {

        /* 布局部分我们这边不多做介绍
         * 这边设置布局为 null
         */
        panel.setLayout(null);

        // 创建 JLabel
        JLabel userLabel = new JLabel("加密密钥:");
        /* 这个方法定义了组件的位置。
         * setBounds(x, y, width, height)
         * x 和 y 指定左上角的新位置，由 width 和 height 指定新的大小。
         */
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        /*
         * 加密密钥
         */
        userText = new JTextField(20);
        userText.setBounds(100, 20, 250, 25);
        //jasypt:encryptor:password:
        userText.setText("9lWaL475Hqwg");
        panel.add(userText);
        // 输入密码的文本域
        JLabel passwordLabel = new JLabel("明文密码:");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        /*
         *这个类似用于输入的文本域
         * 但是输入的信息会以点号代替，用于包含密码的安全性
         */
        passwordText = new JTextField(20);
        passwordText.setBounds(100, 50, 250, 25);
        panel.add(passwordText);


        // 输入密码的文本域
        JLabel passwordLabel1 = new JLabel("加密后密码:");
        passwordLabel1.setBounds(10, 80, 80, 25);
        panel.add(passwordLabel1);


        pwdText = new JTextField(20);
        pwdText.setBounds(100, 80, 250, 25);
        panel.add(pwdText);

        // 创建加密按钮
        JButton loginButton = new JButton("加密");
        loginButton.setBounds(100, 110, 80, 25);
        loginButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub
                System.out.println("mouseReleased");
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub
                System.out.println("mousePressed");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub
                System.out.println("mouseExited");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub
                System.out.println("mouseEntered");
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO Auto-generated method stub
                System.out.println("mouseClicked");
                generatorPwd();
            }
        });
        panel.add(loginButton);


        // 创建解密按钮
        JButton decryptButton = new JButton("解密");
        decryptButton.setBounds(180, 110, 80, 25);
        decryptButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub
                System.out.println("mouseReleased");
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub
                System.out.println("mousePressed");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub
                System.out.println("mouseExited");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub
                System.out.println("mouseEntered");
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO Auto-generated method stub
                decryptPwd();
            }
        });
//        panel.add(decryptButton);
    }

    private static void generatorPwd() {
        //密钥
        String key = userText.getText().trim();
        //密码
        String pwd = passwordText.getText().trim();

        if (key.isEmpty()) {
            JOptionPane.showMessageDialog(null, "请输入加密密钥", "标题", JOptionPane.WARNING_MESSAGE);
            userText.setFocusable(true);
            return;
        }
        if (pwd.isEmpty()) {
            JOptionPane.showMessageDialog(null, "请输入明文密码", "标题", JOptionPane.WARNING_MESSAGE);
            passwordText.setFocusable(true);
            return;
        }

        pwdText.setText(encrypt(key,pwd));
    }

    private static void decryptPwd(){
        //密钥
        String key = userText.getText().trim();
        //密码
        String pwdNew = pwdText.getText().trim();

        if (key.isEmpty()) {
            JOptionPane.showMessageDialog(null, "请输入加密密钥", "标题", JOptionPane.WARNING_MESSAGE);
            userText.setFocusable(true);
            return;
        }
        if (pwdNew.isEmpty()) {
            JOptionPane.showMessageDialog(null, "请输入加密后密码", "标题", JOptionPane.WARNING_MESSAGE);
            pwdText.setFocusable(true);
            return;
        }

        JOptionPane.showMessageDialog(null, decrypt(key,pwdNew), "标题", JOptionPane.WARNING_MESSAGE);
    }

    /**
     * 加密
     * @param key 密钥
     * @param text 明文
     * @return     密文
     */
    private static String encrypt(String key,String text) {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(key);
        return encryptor.encrypt(text);
    }

    /**
     * 解密
     * @param key 密钥
     * @param ciphertext 密文
     * @return           明文
     */
    private static String decrypt(String key,String ciphertext) {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(key);
        return encryptor.decrypt(ciphertext);
    }
}
