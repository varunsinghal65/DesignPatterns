package com.varun;

import javax.swing.plaf.nimbus.State;
import java.math.BigDecimal;

/**
 * creational
 * -factory - done
 * -builder -
 * -singleton -
 * <p>
 * structural
 * - adapter - done
 * - facade - done
 * <p>
 * Behaviorial
 * - state - done
 * - strategy - done
 */

class Driver {
    public static void main(String[] args) {
        Player ipod = new Player();
        ipod.play();
        ipod.stop();
        ipod.play();
        ipod.rewind();
        ipod.rewind();
        ipod.stop();

    }

    //model all transitions in a state interface
    //then we create concrete implementations of this state interface, every impl is a possible state in the machine.
    //then we create a context class that implements the state interface and has variable pointing to the current state interface impl
    //allt he methods in the context class will be delegated to the state interfce impl.
    //the state impl has transition methods that can alter the sintance of the context class and change its current implementation, there by achieving a
    //full state transition without switch and if else, also all the state specific behaviiour now lies in one concrete class
    //(separation of concerns, encapsulate what varies.)

    private interface PlayerState {
        void play(Player context);
        void stop(Player context);
        void rewind(Player context);
    }

    private static class Started implements PlayerState {

        @Override
        public void play(Player context) {
            System.out.println("Already started.");
        }

        @Override
        public void stop(Player context) {
            System.out.println("Player stopped.");
            context.currentState = new Stopped();
        }

        @Override
        public void rewind(Player context) {
            System.out.println("Cannot rewind a player that's already started. Please stop first.");
        }
    }

    private static class Stopped implements PlayerState {

        @Override
        public void play(Player context) {
            System.out.println("Player started.");
            context.currentState = new Started();
        }

        @Override
        public void stop(Player context) {
            System.out.println("Player already stopped.");
        }

        @Override
        public void rewind(Player context) {
            System.out.println("Rewind started.");
            context.currentState = new Rewinding();
        }
    }

    private static class Rewinding implements PlayerState {
        @Override
        public void play(Player context) {
            System.out.println("Player rewinding, stop the player first.");
        }

        @Override
        public void stop(Player context) {
            System.out.println("Player stopped.");
            context.currentState = new Stopped();
        }

        @Override
        public void rewind(Player context) {
            System.out.println("Player is already rewinding.");
        }
    }

    private static class Player {

        private PlayerState currentState;

        Player() {
            currentState = new Started();
        }

        public void play() {
            currentState.play(this);
        }

        public void stop() {
            currentState.stop(this);
        }

        public void rewind() {
            currentState.rewind(this);
        }
    }


}


