package jju.soft;

import java.io.*;

public class goodsObjectIO {
    private goodsData goodsdata;

    public void ObjectOutputStream_(goodsData gd) {
        ObjectOutputStream output;
        try {
            output = new ObjectOutputStream(new FileOutputStream("src\\jju\\soft\\goodsInfo\\goodsdata.dat"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            output.writeObject(gd);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            output.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public goodsData ObjectInputStream_() throws IOException, ClassNotFoundException {

        ObjectInputStream input;
        File file = new File("src\\jju\\soft\\goodsInfo\\goodsdata.dat");
        if(file.exists()){
            try {
                input = new ObjectInputStream(new FileInputStream(file));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            goodsData gd = (goodsData) input.readObject();
            input.close();
            goodsdata = gd;
            return gd;
        } else {
            goodsdata = new goodsData();
            ObjectOutputStream_(new goodsData());
            return goodsdata;
        }

    }
}
