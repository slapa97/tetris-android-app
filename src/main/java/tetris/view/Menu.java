package main.java.tetris.view;

import main.java.tetris.controller.ButtonListner;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Menu extends Frame {
    private Button b;
    public Button getB() {
        return b;
    }

    public void setB(Button b) {
        this.b = b;
    }

    private boolean isClickedStartButton;
    public void setClickedStartButton(boolean clickedStartButton) {
        isClickedStartButton = clickedStartButton;
    }

    public boolean isClickedStartButton() {
        return isClickedStartButton;
    }


    Menu() {
        Label label = new Label("Speed:");
        label.setBounds(100, 300, 200, 100);
        setFont(new Font("Arial", Font.PLAIN, 40));
        add(label);

        Checkbox checkboxSlow = new Checkbox("Slow");
        checkboxSlow.setState(false);
        checkboxSlow.setBounds(300, 300, 100, 100);
        add(checkboxSlow);

        Checkbox checkboxMedium = new Checkbox("Medium");
        checkboxMedium.setState(false);
        checkboxMedium.setBounds(480, 300, 200, 100);
        add(checkboxMedium);

        Checkbox checkboxFast = new Checkbox("Fast");
        checkboxFast.setState(false);
        checkboxFast.setBounds(700, 300, 100, 100);
        add(checkboxFast);

        Button bTmp = new Button("Start");
        setFont(new Font("Arial", Font.PLAIN, 40));
        bTmp.setBounds(450, 600, 180, 100);// setting button position;
        add(bTmp);
        setSize(1000, 800);
        setLayout(null);
        setVisible(true);

    }

//    public static void main(String[] args) {
//        Menu menu = new Menu();
//    }
}
