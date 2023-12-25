package jju.soft;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class goodsManagement {
    goodsData goodsdata;
    public goodsManagement() throws IOException, ClassNotFoundException {
        goodsObjectIO io = new goodsObjectIO();
        goodsdata = io.ObjectInputStream_();

        JFrame goodsFrame = new JFrame("超市商品管理系统");
        goodsBuff buff = new goodsBuff();
        buff.frameBuff(goodsFrame,goodsdata);
        buff.menuBuff();
        buff.operatePanel();

        goodsFrame.setVisible(true);


    }

}

class goodsBuff{
    JFrame goodsFrame;
    JMenuItem money;
    goodsData goodsdata;
    goodsObjectIO io;

    void frameBuff(JFrame goodsFrame, goodsData goodsdata){
        this.goodsFrame = goodsFrame;
        goodsFrame.setBounds(450,150,500,500);
        goodsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        goodsFrame.setLayout(new GridLayout(1,1));
        this.goodsdata = goodsdata;

        io = new goodsObjectIO();
    }

    void menuBuff(){
        JMenuBar account = new JMenuBar();
        JMenu menu = new JMenu("账户");
        money = new JMenuItem("余额："+goodsdata.getMoney()+"￥");

        JMenuItem addMoney = new JMenuItem("充值");
        JMenuItem drawMoney = new JMenuItem("提现");

        menu.add(money);
        menu.add(addMoney);
        menu.add(drawMoney);

        account.add(menu);


        goodsFrame.setJMenuBar(account);


        addMoney.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame addFrame = new JFrame("充值窗口");
                addFrame.setBounds(450,150,500,500);

                JLabel title = new JLabel("请输入充值金额：");
                JTextField text = new JTextField(9);
                JButton button = new JButton("确定");

                JPanel panel = new JPanel();
                panel.add(title);
                panel.add(text);
                panel.add(button);
                addFrame.add(panel);

                addFrame.setVisible(true);

                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        double get = Double.parseDouble(text.getText());
                        goodsdata.setMoney(get+goodsdata.getMoney());
                        io.ObjectOutputStream_(goodsdata);
                        money.setText("余额："+goodsdata.getMoney()+"￥");
                    }
                });

            }
        });


        drawMoney.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame drawFrame = new JFrame("提现窗口");
                drawFrame.setBounds(450,150,500,500);

                JLabel title = new JLabel("请输入提现金额：");
                JTextField text = new JTextField(9);
                JButton button = new JButton("确定");

                JPanel panel = new JPanel();
                panel.add(title);
                panel.add(text);
                panel.add(button);
                drawFrame.add(panel);

                drawFrame.setVisible(true);

                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        double get = Double.parseDouble(text.getText());

                        if (get > goodsdata.getMoney()){
                            JOptionPane.showMessageDialog(drawFrame,"提现金额超出余额，请重试！","余额不足",JOptionPane.WARNING_MESSAGE);
                        } else{
                            goodsdata.setMoney(goodsdata.getMoney()-get);
                            io.ObjectOutputStream_(goodsdata);
                            money.setText("余额："+goodsdata.getMoney());
                        }
                    }
                });


            }
        });

    }

    void operatePanel(){
        JPanel operatePanel = new JPanel(new GridLayout(9,1));
        operatePanel.add(new goodsPanel(goodsdata.water.getGoodsName(),goodsdata.water.getPrice(),goodsdata,goodsFrame,money,goodsdata.water));
        operatePanel.add(new goodsPanel(goodsdata.milk.getGoodsName(),goodsdata.milk.getPrice(),goodsdata,goodsFrame,money,goodsdata.milk));
        operatePanel.add(new goodsPanel(goodsdata.noodles.getGoodsName(),goodsdata.noodles.getPrice(),goodsdata,goodsFrame,money,goodsdata.noodles));
        operatePanel.add(new goodsPanel(goodsdata.chips.getGoodsName(),goodsdata.chips.getPrice(),goodsdata,goodsFrame,money,goodsdata.chips));
        operatePanel.add(new goodsPanel(goodsdata.tissue.getGoodsName(),goodsdata.tissue.getPrice(),goodsdata,goodsFrame,money,goodsdata.tissue));
        operatePanel.add(new goodsPanel(goodsdata.bread.getGoodsName(),goodsdata.bread.getPrice(),goodsdata,goodsFrame,money,goodsdata.bread));
        operatePanel.add(new goodsPanel(goodsdata.apple.getGoodsName(),goodsdata.apple.getPrice(),goodsdata,goodsFrame,money,goodsdata.apple));
        operatePanel.add(new goodsPanel(goodsdata.banana.getGoodsName(),goodsdata.banana.getPrice(),goodsdata,goodsFrame,money,goodsdata.banana));
        operatePanel.add(new goodsPanel(goodsdata.paper.getGoodsName(),goodsdata.paper.getPrice(),goodsdata,goodsFrame,money,goodsdata.paper));
        JScrollPane panel = new JScrollPane(operatePanel);
        goodsFrame.add(panel);
    }

}

class goodsPanel extends JPanel{
    JLabel numLabel;
    public goodsPanel(String s, double price, goodsData goodsdata, JFrame frame, JMenuItem money, goods product){
        setLayout(new GridLayout(5,1));
        JLabel nameLabel = new JLabel("商品："+s);
        add(nameLabel);
        JLabel priceLabel = new JLabel("售价："+price+"￥");
        add(priceLabel);
        numLabel = new JLabel("库存："+product.getNum());
        add(numLabel);

        JPanel outPanel = new JPanel();
        JLabel outLabel = new JLabel("售出：");
        JTextField outText = new JTextField(5);
        JButton outButton = new JButton("确定");
        outPanel.add(outLabel);
        outPanel.add(outText);
        outPanel.add(outButton);
        add(outPanel);

        JPanel inPanel = new JPanel();
        JLabel inLabel = new JLabel("进货：");
        JTextField inText = new JTextField(5);
        JButton inButton = new JButton("确定");
        inPanel.add(inLabel);
        inPanel.add(inText);
        inPanel.add(inButton);
        add(inPanel);

        outButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int n = Integer.parseInt(outText.getText());
                System.out.println(product.getNum());
                if (n > product.getNum()){
                    JOptionPane.showMessageDialog(frame,"库存不足！","库存不足",JOptionPane.WARNING_MESSAGE);
                } else {
                    numLabel.setText("库存："+(product.getNum()-n));
                    product.setNum(product.getNum()-n);
                    goodsdata.setMoney(goodsdata.getMoney()+n*price);
                    money.setText("余额："+goodsdata.getMoney()+"￥");
                    goodsObjectIO io = new goodsObjectIO();
                    io.ObjectOutputStream_(goodsdata);
                }
            }
        });

        inButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int n = Integer.parseInt(inText.getText());

                if (n*price > goodsdata.getMoney()){
                    JOptionPane.showMessageDialog(frame,"资金不足！","资金不足",JOptionPane.WARNING_MESSAGE);
                } else {
                    numLabel.setText("库存："+(product.getNum()+n));
                    product.setNum(product.getNum()+n);
                    goodsdata.setMoney(goodsdata.getMoney()-n*(price-product.getInterest()));
                    money.setText("余额："+goodsdata.getMoney()+"￥");
                    goodsObjectIO io = new goodsObjectIO();
                    io.ObjectOutputStream_(goodsdata);
                }
            }
        });

    }

}
