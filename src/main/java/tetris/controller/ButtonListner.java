package main.java.tetris.controller;

public class ButtonListner {

    private int leftButtonActionCounter;
    private int upButtonActionCounter;
    private int rightButtonActionCounter;

    public int getLeftButtonActionCounter() {
        return leftButtonActionCounter;
    }

    public int getUpButtonActionCounter() {
        return upButtonActionCounter;
    }

    public int getRightButtonActionCounter() {
        return rightButtonActionCounter;
    }



    public ButtonListner(int leftButtonActionCounter, int upButtonActionCounter, int rightButtonActionCounter) {
        this.leftButtonActionCounter = leftButtonActionCounter;
        this.upButtonActionCounter = upButtonActionCounter;
        this.rightButtonActionCounter = rightButtonActionCounter;
    }


    public void setLeftButtonActionCounter(int leftButtonActionCounter) {
        this.leftButtonActionCounter = leftButtonActionCounter;
    }

    public void setUpButtonActionCounter(int upButtonActionCounter) {
        this.upButtonActionCounter = upButtonActionCounter;
    }

    public void setRightButtonActionCounter(int rightButtonActionCounter) {
        this.rightButtonActionCounter = rightButtonActionCounter;
    }


    public  void restetCounters() {
        setLeftButtonActionCounter(0);
        setRightButtonActionCounter(0);
        setUpButtonActionCounter(0);
    }
}
