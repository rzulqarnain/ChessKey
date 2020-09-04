/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import java.awt.Graphics;

public class King extends Piece {

    public King(int player, int row, int column) {
        super(player, row, column);
    }

    @Override
    public String type() {
        return "K";
    }

    @Override
    public boolean isValidMove(int newRow, int newColumn, Board b) {
        boolean result = true;

        Piece target = b.piece(newRow, newColumn);
        if (target != null && target.player == player) {  //Can't cut pieces of same player
            result = false;
        } else {
            result = Math.abs(row - newRow) < 2 && Math.abs(column - newColumn) < 2;
        }
        return result;
    }

    @Override
    public void draw(Graphics g, int x, int y) {
        super.draw(g, x, y);
        g.drawString(toString(), x, y);
    }
}
