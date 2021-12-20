package com.example.leprac13;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.Arrays;

public class HelloController {

    @FXML
    Label instruction;


    @FXML
    private ArrayList<Label> labelList ;


    private final boolean[] checkPlayer1 = {false,false,false,false,false,false,false,false,false};
    private final boolean[] checkPlayer2 = {false,false,false,false,false,false,false,false,false};

    private boolean trigger = true;

    public boolean checkWin(boolean[] check){
        if ((check[0]&check[1]&check[2]) || (check[3]&check[4]&check[5]) || (check[6]&check[7]&check[8]) || (check[0]&check[3]&check[6]) || (check[1]&check[4]&check[7])
                || (check[2]&check[5]&check[8]) || (check[0]&check[4]&check[8]) || (check[6]&check[4]&check[2]))
            return true;
        return false;
    }

    public boolean checkDeadHeat(){
        ArrayList<Label> list = new ArrayList<>(labelList);
        list.removeIf(s -> s.isDisable());
        return list.size() == 0;
    }

    public void onMouseClick(MouseEvent mouseEvent){
        Label label = (Label) mouseEvent.getSource();
        if (trigger) {
            label.setText("X");
            trigger = false;
            checkPlayer1[Integer.parseInt(label.getId().substring(1))] = true;
            instruction.setText("Your turn Player 2!");
            if(checkWin(checkPlayer1)) {
                if (!trigger)
                    instruction.setText("Player 1 has won!");
                else
                    instruction.setText("Player 2 has won!");
                labelList.forEach(s -> s.setDisable(true));
                label.setOpacity(1);
                return;
            }
        }
        else {
            label.setText("O");
            trigger = true;
            checkPlayer2[Integer.parseInt(label.getId().substring(1))] = true;
            instruction.setText("Your turn Player 1!");
            if(checkWin(checkPlayer2)) {
                if (!trigger)
                    instruction.setText("Player 1 has won!");
                else
                    instruction.setText("Player 2 has won!");
                labelList.forEach(s -> s.setDisable(true));
                label.setOpacity(1);
                return;
            }
        }

        label.setDisable(true);
        label.setOpacity(1);

        if(checkDeadHeat())
            instruction.setText("Draw!");

    }
}