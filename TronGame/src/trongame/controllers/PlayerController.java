/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trongame.controllers;

import java.awt.event.KeyListener;
import trongame.ournew.MovementDirection;

/**
 *
 * @author Drimal
 */
public interface PlayerController extends KeyListener {

    /**
     * Return enum represents current player direction of movement
     *
     * @return
     */
    public MovementDirection getPlayerDirection();

    /**
     * Change current player direction of movement
     */
    public void setPlayerDirection(MovementDirection direction);
}
