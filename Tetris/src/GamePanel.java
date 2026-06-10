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
    private int score = 0;
    private int level = 1;
    private int lines = 0;
    private boolean gameOver = false;

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

            if (!gameOver) {

                update();
            }

            repaint();

            try {

                Thread.sleep(
                        Math.max(
                                100,
                                500 - ((level - 1) * 50)
                        )
                );

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

                            case KeyEvent.VK_R -> {

                                if (gameOver) {

                                    restartGame();
                                }
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

        clearLines();

        spawnNewPeice();
    }

    private void update() {

        if (!currentPiece.moveDown(board)) {
            currentPiece.lock(board);
            clearLines();
            spawnNewPeice();
        }
    }

    private void clearLines() {

        int linesCleared = 0;

        for (int row = 19; row >= 0; row--) {
            boolean full = true;

            for (int col = 0; col < 10; col++) {
                if (board[row][col] == 0) {
                    full = false;
                    break;
                }
            }

            if (full) {
                removeLine(row);
                row++;
                linesCleared++;
            }
        }

        switch (linesCleared) {
            case 1 -> score += 100 * level;
            case 2 -> score += 300 * level;
            case 3 -> score += 500 * level;
            case 4 -> score += 800 * level;
        }

        lines += linesCleared;

        level = (lines / 10) + 1;
    }

    private void removeLine(int rowToRemove) {
        for (int row = rowToRemove; row > 0; row--) {
            for (int col = 0; col < 10; col++) {
                board[row][col] = board[row - 1][col];
            }
        }

        for (int col = 0; col < 10; col++) {
            board[0][col] = 0;
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

        g.setFont(new Font(
                "Arial",
                Font.BOLD,
                20
        ));
        g.setColor(Color.WHITE);
        g.drawString("Score: "+ score, 10, 20);
        g.drawString("Level: "+ level, 10, 45);
        g.drawString("Lines: "+ lines, 10, 70);

        if (gameOver) {
            g.setColor(Color.RED);
            g.setFont(new Font(
                    "Arial",
                    Font.BOLD,
                    40
            ));
            g.drawString("GAME OVER", 35, HEIGHT / 2);
        }

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

    private void spawnNewPeice() {
        currentPiece = Piece.randomPiece();

        if (!currentPiece.canSpawn(board)) {

            gameOver = true;
        }
    }

    private void restartGame() {
        board = new int[20][10];

        score = 0;
        level = 1;
        lines = 0;

        gameOver = false;

        currentPiece = Piece.randomPiece();
    }
}
