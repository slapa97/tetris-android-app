package main.java.tetris.view;

import main.java.tetris.controller.ButtonListner;
import main.java.tetris.model.BoardCell;
import main.java.tetris.model.Game;
import main.java.tetris.model.PieceType;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tetris extends Canvas {

    private Game game = new Game();
    private final BufferStrategy strategy;
    private ButtonListner buttonListner = new ButtonListner(0, 0, 0);

    private final int BOARD_CORNER_X = 300;
    private final int BOARD_CORNER_Y = 50;

    private long lastIteration = System.currentTimeMillis();

    private static final int PIECE_WIDTH = 15;
    JButton leftButton;
    JButton rightButton;
    JButton downButton;
    JButton upButton;

    public Tetris() {
        JFrame container = new JFrame("Tetris,");
        JPanel panel = (JPanel) container.getContentPane();

        createButtons();

        container.add(leftButton);
        container.add(rightButton);
        container.add(upButton);
        container.add(downButton);


        panel.setPreferredSize(new Dimension(1000, 800));
        panel.setLayout(null);
        setBounds(0, 0, 1000, 800);
        panel.add(this);

        setIgnoreRepaint(true);

        container.pack();
        container.setResizable(false);
        container.setVisible(true);

        container.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        requestFocus();
        createBufferStrategy(2);
        strategy = getBufferStrategy();
    }

    private void createButtons() {
        leftButton = new JButton("Left");
        rightButton = new JButton("Right");
        downButton = new JButton("Down");
        upButton = new JButton("Up");
        leftButton.setBounds(100, 470, 180, 100);
        leftButton.setFont(new Font("Arial", Font.PLAIN, 40));
        rightButton.setBounds(470, 470, 180, 100);
        rightButton.setFont(new Font("Arial", Font.PLAIN, 40));
        upButton.setBounds(285, 365, 180, 100);
        upButton.setFont(new Font("Arial", Font.PLAIN, 40));
        downButton.setBounds(285, 470, 180, 100);
        downButton.setFont(new Font("Arial", Font.PLAIN, 40));
    }

    void gameLoop() {
        int counter = 0;
        while (true) {

            if (counter == 0) {
                game = new Game();
                game.startGame();
                counter++;
            }
            if (game.isPlaying()) {
                tetrisLoop();
                buttonListner.restetCounters();
            }
            try {
                Thread.sleep(20);
            } catch (Exception e) {
            }
            draw();
        }
    }


    void tetrisLoop() {
        if (game.isDropping()) {
            game.moveDown();
        } else if (System.currentTimeMillis() - lastIteration >= game.getIterationDelay()) {
            game.moveDown();
            lastIteration = System.currentTimeMillis();
        }

        leftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (buttonListner.getLeftButtonActionCounter() < 1) {
                    game.moveLeft();
                    buttonListner.setLeftButtonActionCounter(1);
                }
            }
        });

        rightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (buttonListner.getRightButtonActionCounter() < 1) {
                    game.moveRight();
                    buttonListner.setRightButtonActionCounter(1);
                }
            }
        });
        upButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (buttonListner.getUpButtonActionCounter() < 1) {
                    game.rotate();
                    buttonListner.setUpButtonActionCounter(1);
                }
            }
        });
        downButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                game.drop();
            }
        });
    }


    public void draw() {
        Graphics2D g = getGameGraphics();
        drawEmptyBoard(g);
        if (game.isPlaying()) {
            drawCells(g);
        }
        if (game.isGameOver()) {
            drawCells(g);
            drawGameOver(g);
        }
        drawStatus(g);
        g.dispose();
        strategy.show();
    }

    private Graphics2D getGameGraphics() {
        return (Graphics2D) strategy.getDrawGraphics();
    }

    private void drawCells(Graphics2D g) {
        BoardCell[][] cells = game.getBoardCells();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 20; j++) {
                drawBlock(g, BOARD_CORNER_X + i * 15, BOARD_CORNER_Y + (19 - j) * 15, getBoardCellColor(cells[i][j]));
            }
        }
    }

    private void drawEmptyBoard(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 1000, 800);
        g.setColor(Color.GRAY);
        g.drawRect(BOARD_CORNER_X - 1, BOARD_CORNER_Y - 1, 10 * PIECE_WIDTH + 2, 20 * PIECE_WIDTH + 2);
    }

    private void drawStatus(Graphics2D g) {
        g.setFont(new Font("Dialog", Font.PLAIN, 30));
        g.setColor(Color.WHITE);
        g.drawString("Score", 465, 200);
        g.drawString(getScore(), 465, 235);
    }

    private void drawGameOver(Graphics2D g) {
        Font font = new Font("Dialog", Font.PLAIN, 16);
        g.setFont(font);
        g.setColor(Color.WHITE);
        g.drawString("GAME OVER", 350, 550);
    }

    private String getScore() {
        if (game.getTotalScore() < 100) {
            return String.format("0000000%1s", game.getTotalScore());
        } else if (game.getTotalScore() > 100) {
            return String.format("000000%1s", game.getTotalScore());
        } else if (game.getTotalScore() > 1000) {
            return String.format("0000%1s", game.getTotalScore());
        } else if (game.getTotalScore() > 10000) {
            return String.format("000%1s", game.getTotalScore());
        } else {
            return String.format("00%1s", game.getTotalScore());
        }
    }

    private Color getBoardCellColor(BoardCell boardCell) {
        if (boardCell.isEmpty()) {
            return Color.BLACK;
        }
        return getPieceColor(boardCell.getPieceType());
    }

    private Color getPieceColor(PieceType pieceType) {
        switch (pieceType) {
            case I:
                return Color.RED;
            case J:
                return Color.GRAY;
            case L:
                return Color.CYAN;
            case O:
                return Color.BLUE;
            case S:
                return Color.GREEN;
            default:
                return Color.MAGENTA;
        }
    }

    private void drawBlock(Graphics g, int x, int y, Color color) {
        g.setColor(color);
        g.fillRect(x, y, PIECE_WIDTH, PIECE_WIDTH);
        g.drawRect(x, y, PIECE_WIDTH, PIECE_WIDTH);
    }


    public static void main(String[] args) {

        new Tetris().gameLoop(); ////nie wiem czemu nie dziala ????

//        Menu menu = new Menu();
//        menu.getB().addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent actionEvent) {
//                new Tetris().gameLoop();
//            }
//        });



    }

}