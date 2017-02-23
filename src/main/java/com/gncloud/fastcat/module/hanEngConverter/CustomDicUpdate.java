package com.gncloud.fastcat.module.hanEngConverter;

import java.io.*;

/**
 * Created by gncloud on 2017-02-21.
 *
 */
public class CustomDicUpdate {
    public void insertKeyword(String keyword){
        int match = 0;
        String filepath = "./dic/custom.noun.txt";
        BufferedReader in = null;
        FileWriter fw = null;
        try {
            in = new BufferedReader(new FileReader(filepath));
            String s = "";
            while((s = in.readLine())!=null){
                if(s.equalsIgnoreCase(keyword)){
                    match = 1;
                    break;
                }
            }
            in.close();
            if(match != 1) {
                fw = new FileWriter(filepath, true);
                fw.write("\n"+keyword);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(in != null && fw !=null){
                try {
                    in.close();
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
