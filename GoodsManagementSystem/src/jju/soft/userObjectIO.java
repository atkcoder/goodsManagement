package jju.soft;

import java.io.*;

public class userObjectIO {
    private userData userdata;
    public userObjectIO(){
        userdata = null;
    }

    public userObjectIO(userData userdata){
        this.userdata = userdata;
    }
    public void ObjectOutputStream_() {
        ObjectOutputStream output;
        try {
            output = new ObjectOutputStream(new FileOutputStream("src\\jju\\soft\\userInfo\\"+userdata.getUserName()+".dat"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            output.writeObject(userdata);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            output.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public userData ObjectInputStream_(String s) throws IOException, ClassNotFoundException {

        ObjectInputStream input;
        File file = new File("src\\jju\\soft\\userInfo\\"+s+".dat");
        if(file.exists()){
            try {
                input = new ObjectInputStream(new FileInputStream(file));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            userData ud = (userData) input.readObject();
            input.close();
            return ud;
        }

        return null;
    }

}
