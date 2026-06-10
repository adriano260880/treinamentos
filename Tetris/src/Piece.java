import java.awt.*;
import java.util.Random;

public class Piece {

    private int[][] shape;

    private int x;
    private int y;

    private final Color color;

    private static final Random RANDOM = new Random();

    public Piece(
            int[][] shape,
            Color color
    ) {
        this.shape = copyMatrix(shape);
        this.color = color;
        this.x = 3;
        this.y = 0;
    }

    public int[][] getShape() {
        return shape;
    }

    public Color getColor() {
        return color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void moveLeft(int[][] board) {
        if (!collision(shape, x - 1, y, board)) {
            x--;
        }
    }

    public void moveRight(int[][] board) {
        if (!collision(shape, x + 1, y, board)) {
            x++;
        }
    }

    public boolean moveDown(int[][] board) {
        if (!collision(shape, x, y + 1, board)) {
            y++;

            return true;
        }

        return false;
    }

    public void rotate(int [][] board) {

        int[][] rotated = rotateMatrix(shape);

        if (!collision(rotated, x, y, board)) {
            shape = rotated;
        }
    }

    public boolean canSpawn(int[][] board) {

        for (int row = 0; row < shape.length; row++) {

            for (int col = 0; col < shape[row].length; col++) {

                if (shape[row][col] == 0) {
                    continue;
                }

                int boardX = x + col;
                int boardY = y + row;

                if (boardY >= 0 && board[boardY][boardX] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean collision(
            int[][] testShape,
            int nextX,
            int nextY,
            int[][] board
    ) {
        for (int row = 0; row < testShape.length; row++) {
            for (int col = 0; col < testShape[row].length; col++) {
                if (testShape[row][col] == 0) {
                    continue;
                }

                int boardX = nextX + col;
                int boardY = nextY + row;

                if (boardX < 0 || boardX >= 10) {
                    return true;
                }

                if (boardY >= 20) {
                    return true;
                }

                if (boardY >= 0 && board[boardY][boardX] != 0) {
                    return true;
                }
            }
        }
        return false;
    }

    private int[][] rotateMatrix(int[][] matrix) {

        int rows = matrix.length;

        int cols = matrix[0].length;

        int[][] rotated = new int[cols][rows];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                rotated[col][rows - 1 - row] = matrix[row][col];
            }
        }
        return rotated;
    }

    public int[][] copyMatrix(int[][]original) {

        int[][] copy = new int[original.length][];

        for (int i = 0; i < original.length; i++) {

            copy[i] = original[i].clone();
        }
        return copy;
    }

    public static Piece randomPiece() {

        return switch (
                RANDOM.nextInt(7)
                ) {

            case 0 ->
                    new Piece(

                            new int[][]{

                                    {1,1,1,1}

                            },

                            Color.CYAN
                    );

            case 1 ->
                    new Piece(

                            new int[][]{

                                    {1,1},
                                    {1,1}

                            },

                            Color.YELLOW
                    );

            case 2 ->
                    new Piece(

                            new int[][]{

                                    {0,1,0},
                                    {1,1,1}

                            },

                            Color.MAGENTA
                    );

            case 3 ->
                    new Piece(

                            new int[][]{

                                    {1,0},
                                    {1,0},
                                    {1,1}

                            },

                            Color.ORANGE
                    );

            case 4 ->
                    new Piece(

                            new int[][]{

                                    {0,1},
                                    {0,1},
                                    {1,1}

                            },

                            Color.BLUE
                    );

            case 5 ->
                    new Piece(

                            new int[][]{

                                    {0,1,1},
                                    {1,1,0}

                            },

                            Color.GREEN
                    );

            default ->
                    new Piece(

                            new int[][]{

                                    {1,1,0},
                                    {0,1,1}

                            },

                            Color.RED
                    );
        };
    }

    public void lock(
            int[][] board
    ) {

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

                    int boardX =
                            x + col;

                    int boardY =
                            y + row;

                    if (
                            boardY >= 0
                    ) {

                        board[boardY]
                                [boardX]
                                = 1;
                    }
                }
            }
        }
    }
}
