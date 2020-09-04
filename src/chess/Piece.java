/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import java.awt.Graphics;

public abstract class Piece {

    //Constructors
    public Piece(int player, int row, int column) {
        this.player = player;
        this.row = row;
        this.column = column;
    }

    //Accessors
    abstract public String type();  //P=Pawn, R=Rook, N=Knight, B=Bishop, Q=Queen, K=King

    abstract boolean isValidMove(int newRow, int newColumn, Board b); //True if piece can move to (row,column)

    @Override
    public String toString() {
        String s = (player == WHITE) ? "W" : "B";
        return s + type();
    }

    public int row() {
        return row;
    }

    public int column() {
        return column;
    }

    public int player() {
        return player;
    }

    public void draw(Graphics g, int x, int y) {
        g.setColor(Board.squareColor(row, column + 1));
    }

    //Mutators
    public void move(int row, int column) {
        this.row = row;
        this.column = column;
    }

    //protected data members
    protected final int player;     //WHITE or BLACK
    protected int row;              //row 0 is top most row
    protected int column;           //column 0 is left most column

    public static final int WHITE = +1;
    public static final int BLACK = -1;
}
