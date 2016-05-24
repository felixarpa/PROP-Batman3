package view;

import javafx.scene.text.Font;

/**
 * Created by carlos.roldan on 17/05/2016.
 */
public class prueba {

    public static void main(String[] args){

        for(String s : Font.getFamilies()){
            System.out.println(s);
        }
    }
}
