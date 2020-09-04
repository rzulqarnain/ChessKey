/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import java.awt.Graphics;

public class Pawn extends Piece {

    public Pawn(int player, int row, int column) {
        super(player, row, column);
        firstTime = true;
    }

    @Override
    public String type() {
        return "P";
    }

    @Override
    public boolean isValidMove(int newRow, int newColumn, Board b) {
//System.out.println("row="+row+" newRow="+newRow+" column="+column+" newColumn"+newColumn);
        boolean result = true;

        Piece target = b.piece(newRow, newColumn);
        if (target != null && target.player == player) {  //Can't cut pieces of same player
            result = false;
        } else if (firstTime && row == newRow + 2 * player) {
            result = target == null;
        } else if (row != newRow + player) {
            result = false;
        } else if (target == null) {
            result = column == newColumn;
        } else {
            result = target.player != player && Math.abs(column - newColumn) == 1;
        }
        if (result && firstTime) {
            firstTime = false;
        }
        return result;
    }

    @Override
    public void draw(Graphics g, int x, int y) {
        super.draw(g, x, y);
        g.drawString(toString(), x, y);
    }
    private boolean firstTime;
}
