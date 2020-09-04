/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import java.awt.Graphics;

public class Bishop extends Piece {

    public Bishop(int player, int row, int column) {
        super(player, row, column);
    }

    @Override
    public String type() {
        return "B";
    }

    @Override
    public boolean isValidMove(int newRow, int newColumn, Board b) {
        boolean result = true;
        int i;
        int j;
        Piece target = b.piece(newRow, newColumn);
        if (target != null && target.player == player) {  //Can't cut pieces of same player
            result = false;
        } else if (row - column != newRow - newColumn && row + column != newRow + newColumn) {
            result = false;
        } else if (row < newRow && column < newColumn) {
            for (i = row + 1, j = column + 1; i < newRow && j < newColumn; i++, j++) {
                if (b.piece(i, j) != null) {    //There's a piece in the way
                    result = false;
                    break;
                }
            }
            result = (result && !(i + 1 == newRow && j + 1 == newColumn));
        } else if (row < newRow && column > newColumn) {
            for (i = row + 1, j = column - 1; i < newRow && j > newColumn; i++, j--) {
                if (b.piece(i, j) != null) {    //There's a piece in the way
                    result = false;
                    break;
                }
            }
            result = (result && !(i + 1 == newRow && j - 1 == newColumn));
        } else if (row > newRow && column > newColumn) {
            for (i = row - 1, j = column - 1; i > newRow && j > newColumn; i--, j--) {
                if (b.piece(i, j) != null) {    //There's a piece in the way
                    result = false;
                    break;
                }
            }
            result = (result && !(i - 1 == newRow && j - 1 == newColumn));
        } else if (row > newRow && column < newColumn) {
            for (i = row - 1, j = column + 1; i > newRow && j < newColumn; i--, j++) {
                if (b.piece(i, j) != null) {    //There's a piece in the way
                    result = false;
                    break;
                }
            }
            result = (result && !(i - 1 == newRow && j + 1 == newColumn));
        } else {
            result = false;
        }
        return result;
    }

    @Override
    public void draw(Graphics g, int x, int y) {
        super.draw(g, x, y);
        g.drawString(toString(), x, y);
    }
}
