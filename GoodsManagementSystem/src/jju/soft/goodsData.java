package jju.soft;

import java.io.Serializable;

class goods implements Serializable{
    private String goodsName;
    private int num;
    private double price;
    private double interest;


    public goods(String s){
        setGoodsName(s);
        setNum(0);
        setPrice(0);
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }
}

public class goodsData implements Serializable{
    private double money;
    goods water, milk, noodles, chips, tissue, bread, apple, banana, paper;
    public goodsData(){
        water = new goods("农夫山泉");
        water.setPrice(2);
        water.setInterest(1);
        milk = new goods("蒙牛纯牛奶");
        milk.setPrice(4.5);
        milk.setInterest(1.5);
        noodles = new goods("康师傅牛肉面");
        noodles.setPrice(5);
        noodles.setInterest(2);
        chips = new goods("可比克薯片");
        chips.setPrice(3.5);
        chips.setInterest(1);
        tissue = new goods("心相印纸巾");
        tissue.setPrice(1);
        tissue.setInterest(0.5);
        bread = new goods("达利园面包");
        bread.setPrice(2);
        bread.setInterest(1);
        apple = new goods("苹果");
        apple.setPrice(5);
        apple.setInterest(3);
        banana = new goods("香蕉");
        banana.setPrice(2);
        banana.setInterest(1);
        paper = new goods("作业本");
        paper.setPrice(1);
        paper.setInterest(0.5);
    }

    String getGoodsName(){
        return null;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
