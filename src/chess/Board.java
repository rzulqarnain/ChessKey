/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import java.awt.Color;
import java.awt.Graphics;

public class Board {
    //Constructors
    /**
     * Create a new chess board by drawing the checker patterned board and
     * placing pieces in the starting positions
     *
     * @param chessPanel
     */
    public Board(javax.swing.JPanel chessPanel) {
        gameBoard = new Piece[8][8];
        gameArea = chessPanel.getGraphics();
        size = chessPanel.getWidth() / 8;
        placePieces();
    }

    //Accessors
    /**
     * Redraw the current board and pieces
     */
    public void draw() {
        drawEmptyBoard();
        drawPiecesOnBoard();
    }

    /**
     * Draw an empty chess board of alternating white and black squares
     */
    private void drawEmptyBoard() {
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                gameArea.setColor(squareColor(row, column));
                gameArea.fillRect(row * size, column * size, size, size);
            }
        }
    }

    /**
     * Draw the pieces on the chess board
     */
    public void drawPiecesOnBoard() {
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                Piece p = gameBoard[row][column];
                if (p != null) {
                    p.draw(gameArea, column * size + size / 2, row * size + size / 2);
                }
            }
        }
    }

    /**
     * Return color of given square
     *
     * @param row
     * @param column
     * @return Color.white or Color.black
     */
    public static Color squareColor(int row, int column) {
        return ((row + column) % 2 == 0) ? Color.white : Color.black;
    }

    /**
     * Return the row index of mouse pointer Y location
     *
     * @param y
     * @return
     */
    public int row(int y) {
        return y / size;
    }

    /**
     * Return the column index of mouse pointer X location
     *
     * @param x
     * @return
     */
    public int column(int x) {
        return x / size;
    }

    /**
     * Return game piece at given row,column location, or null if none
     *
     * @param row
     * @param column
     * @return
     */
    public Piece piece(int row, int column) {
        return gameBoard[row][column];
    }

    //Mutators
    /**
     * Place the chess pieces to start a game
     */
    private void placePieces() {
        makePieces(Piece.BLACK, 1);
        makePieces(Piece.WHITE, 6);
    }

    /**
     * Place initial pieces with pawns in row 1 or row 6
     *
     * @param player
     * @param pawnRow
     */
    private void makePieces(int player, int pawnRow) {
        int row = pawnRow == 1 ? 0 : 7;
        gameBoard[row][0] = new Rook(player, row, 0);
        gameBoard[row][1] = new Knight(player, row, 1);
        gameBoard[row][2] = new Bishop(player, row, 2);
        gameBoard[row][3] = new Queen(player, row, 3);
        gameBoard[row][4] = new King(player, row, 4);
        gameBoard[row][5] = new Bishop(player, row, 5);
        gameBoard[row][6] = new Knight(player, row, 6);
        gameBoard[row][7] = new Rook(player, row, 7);
        for (int column = 0; column < 8; column++) {
            gameBoard[pawnRow][column] = new Pawn(player, pawnRow, column);
        }
    }

    /**
     * Move given piece from current location to given row,column if allowed
     *
     * @param p is piece to move
     * @param newRow is target row to move to
     * @param newColumn is target column to move to
     * @return True only if move succeeded
     */
    public boolean move(Piece p, int newRow, int newColumn) {
        boolean allowed = p.isValidMove(newRow, newColumn, this);
        if (allowed) {
            gameBoard[p.row()][p.column()] = null;
            p.move(newRow, newColumn);
            gameBoard[newRow][newColumn] = p;
            draw();
        }
        return allowed;
    }

    //Private data members
    private final Piece[][] gameBoard;    //Copies of current game pieces
    private final Graphics gameArea;      //Drawing area of game board
    private final int size;               //Size of each board square
}
