import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel implements Runnable {

    static final int WIDTH = 300;
    static final int HEIGHT = 600;

    private Piece currentPiece;
    private int[][] board = new int[20][10];
    private Thread gameThead;
    private boolean running;

    public GamePanel() {
        setPreferredSize(
                new Dimension(WIDTH, HEIGHT)
        );

        setBackground(Color.BLACK);
        setFocusable(true);

        currentPiece = Piece.randomPiece();

        setupKeyboard();
    }

    public void startGame() {

        running = true;

        requestFocusInWindow();

        gameThead = new Thread(this);

        gameThead.start();
    }

    @Override
    public void run() {

        while (running) {

            update();

            repaint();

            try {

                Thread.sleep(500);

            } catch (
                    InterruptedException e
            ) {

                Thread.currentThread()
                        .interrupt();
            }
        }
    }

    private void setupKeyboard() {

        addKeyListener(
                new KeyAdapter() {

                    @Override
                    public void keyPressed(
                            KeyEvent e
                    ) {

                        switch (
                                e.getKeyCode()
                        ) {

                            case KeyEvent.VK_LEFT -> {

                                currentPiece.moveLeft(
                                        board
                                );
                            }

                            case KeyEvent.VK_RIGHT -> {

                                currentPiece.moveRight(
                                        board
                                );
                            }

                            case KeyEvent.VK_DOWN -> {

                                currentPiece.moveDown(
                                        board
                                );
                            }

                            case KeyEvent.VK_UP -> {

                                currentPiece.rotate(
                                        board
                                );
                            }

                            case KeyEvent.VK_SPACE -> {

                                hardDrop();
                            }

                        }

                        repaint();
                    }

                });

    }

    private void hardDrop() {

        while (
                currentPiece.moveDown(
                        board
                )
        ) {

        }

        currentPiece.lock(board);

        currentPiece =
                Piece.randomPiece();
    }

    private void update() {
        if (!currentPiece.moveDown(board)) {
            currentPiece.lock(board);
            currentPiece = Piece.randomPiece();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        draw(g);
    }

    private void draw(Graphics g) {

        g.setColor(Color.GRAY);

        // grade

        for (int y = 0; y < HEIGHT; y += 30) {

            for (int x = 0; x < WIDTH; x += 30) {

                g.drawRect(x, y, 30, 30);
            }
        }

        // peças travadas

        for (int row = 0; row < 20; row++) {

            for (int col = 0; col < 10; col++) {

                if (board[row][col] != 0) {

                    g.setColor(Color.WHITE);

                    g.fillRect(
                            col * 30,
                            row * 30,
                            30,
                            30
                    );

                    g.setColor(Color.BLACK);

                    g.drawRect(
                            col * 30,
                            row * 30,
                            30,
                            30
                    );
                }
            }
        }

        // peça atual

        drawPiece(g);
    }

    private void drawPiece(Graphics g) {

        int[][] shape =
                currentPiece.getShape();

        g.setColor(
                currentPiece.getColor()
        );

        for (
                int row = 0;
                row < shape.length;
                row++
        ) {

            for (
                    int col = 0;
                    col < shape[row].length;
                    col++
            ) {

                if (
                        shape[row][col]
                                == 1
                ) {

                    int x =
                            (currentPiece.getX()
                                    + col)
                                    * 30;

                    int y =
                            (currentPiece.getY()
                                    + row)
                                    * 30;

                    g.fillRect(
                            x,
                            y,
                            30,
                            30
                    );

                    g.setColor(
                            Color.BLACK
                    );

                    g.drawRect(
                            x,
                            y,
                            30,
                            30
                    );

                    g.setColor(
                            currentPiece.getColor()
                    );
                }
            }
        }
    }
}
