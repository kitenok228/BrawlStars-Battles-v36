/************************************************************
 ** Title:  Игра змейка 
 ** Class:  Snake
 ** Author: N/A
 **   Copyright (c) 2007 .
 **   Permission is granted to copy, distribute and/or
 **   modify  this  document under  the  terms  of the
 **   GNU Free Documentation License
 ** Notes: Класс окна игры змейка
 ************************************************************/
package game.snake;

import java.awt.*;
import java.util.ArrayList;

/**
 * Class of the snake. 
 * Snake moved to separate class, so in the future be able to create multiple instances at the same time
 * @autor iliusvla
 * @return snake to gameplay window
 */
public class Snake {

    public static final int DIR_POUSE = 0;
    public static final int DIR_UP = 1;
    public static final int DIR_RIGHT = 2;
    public static final int DIR_DOWN = 3;
    public static final int DIR_LEFT = 4;
    private ArrayList<Point> body = new ArrayList<Point>();
    private int bodySize;
    private int direction = DIR_POUSE;

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    /** 
     * @param x0 X-coordinates
     * @param y0 Y-coordinates
     * @param sz Size of snake
     */
    Snake(int x0, int y0, int sz) {
        bodySize = sz;
        int x = x0 * sz + 2;
        int y = y0 * sz + 2;
        for (int i = 0; i < 1; i++) {
            body.add(new Point(x, y));
        }
    }

    public ArrayList<Point> getBody() {
        return body;
    }

    /**
     * Render snake on gameplay window(dialog)
     * @param g2 Graphics2D
     */
    public void paint(Graphics2D g2) {
        for (Point p : body) {
            g2.setColor(Color.blue);
            g2.fillArc(p.x, p.y, bodySize, bodySize, 0, 360);
            g2.setColor(Color.magenta);
            g2.drawArc(p.x, p.y, bodySize, bodySize, 0, 360);
        }
        Point p = body.get(body.size() - 1);
        g2.setColor(Color.black);
        g2.fillArc(p.x + bodySize / 2 - 2, p.y + bodySize / 2 - 2, 4, 4, 0, 360);
        g2.setColor(Color.white);
        g2.fillArc(p.x + bodySize / 2 - 1, p.y + bodySize / 2 - 1, 2, 2, 0, 360);
    }

    /**
     * Return new snake position and void checking for self-eating
     * @return New snake position
     */
    public Point move() {
        Point last = body.get(body.size() - 1);
        Point pp = last;
        switch (direction) {
            case DIR_POUSE:
                break;
            case DIR_UP:
                body.remove(0);
                pp = new Point(last.x, last.y - bodySize);
                body.add(pp);
                delete();
                break;
            case DIR_RIGHT:
                body.remove(0);
                pp = new Point(last.x + bodySize, last.y);
                body.add(pp);
                delete();
                break;
            case DIR_DOWN:
                body.remove(0);
                pp = new Point(last.x, last.y + bodySize);
                body.add(pp);
                delete();
                break;
            case DIR_LEFT:
                body.remove(0);
                pp = new Point(last.x - bodySize, last.y);
                body.add(pp);
                delete();
                break;
        }
        return pp;
    }

    /**
     * 
     * @return time for Thread.sleep
     * @link SnakeGame#SnakeGame()
     * @see SnakeGame#SnakeGame
     */
    public int getSpeed() {
        return 410;
    }

    /**
     * Add fruit to snake  
     * @link SnakeGame#gameCycle()
     * @see SnakeGame#gameCycle
     * 
     */
    public void expand() {
        body.add(0, new Point(body.get(0).x, body.get(0).y));
    }

    /**
     * Check self-eating of snake
     * @link move
     * @see  move()
     */
    public void delete() {
        for (int i = 1; i < body.size(); i++) {
            if (body.get(i).equals(body.get(0))) {
                body.removeAll(body.subList(i, body.size()));
            }
        }
    }
}
