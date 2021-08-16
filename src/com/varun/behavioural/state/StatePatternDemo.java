package com.varun.behavioural.state;


/**
 * Type :
 * Behavioural (it's used to manage algorithms, relationships and responsibilities between objects.)
 * <p>
 * Other names :
 * pata nahi
 * <p>
 * Purpose:
 * if an object can have a finite number of states, finite number of transitions among those states,
 * and those transitions can be called on any of the state, in such a scenario, impl of your transition methods
 * have to execute state dependent logic and thus have to identify states with long "if else" OR switch constructs.
 * <p>
 * Their state pattern can be used to reduce the number of ifs and switch case statements
 * <p>
 * VS strategy pattern :
 * Strategy also swaps implementations, just like we do in state pattern, however key diff is that in state pattern
 * each state is linked to another and create the flow as in Finite State Machine.
 * <p>
 * How to use :
 * -The class of object containing the finite states, will now be called a new class - Context
 * -Context class will have one variable of type State --> current state
 * -State will not be an enum but instead will be an interface/ abstract class.
 * -State interface will contain all the finite transitions as abstract methods.
 * -We will now create a concrete class for EACH finite state and make it implement state interface.
 * -Once done, we will create all the transition methods in the context class, but in their impl, we will
 * delegate the call to the transition method of the current state.
 * <p>
 * Source URL :
 * https://blogs.oracle.com/javamagazine/the-state-pattern
 * https://refactoring.guru/design-patterns/state
 *
 * @author Varrox
 */
public class StatePatternDemo {

    public static void main(String[] args) {
        UnmaintainablePlayer musicPlayer = new UnmaintainablePlayer();
        musicPlayer.start();
        musicPlayer.stop();
        musicPlayer.rewind();
        musicPlayer.start();
        System.out.println("Final state: " + musicPlayer.currentState);

        MaintainablePlayer musicPlayer1 = new MaintainablePlayer();
        musicPlayer1.start();
        musicPlayer1.stop();
        musicPlayer1.rewind();
        musicPlayer1.start();
        System.out.println("Final state: " + musicPlayer1.currentState);
    }

}

class UnmaintainablePlayer {

    UnmaintainablePlayer() {
        System.out.println("UNMAINTINABLE PLAYER CALLED");
    }

    //finite states possible for Player
    enum State {STARTED, STOPPED, REWINDING}

    State currentState;
    //finite transitions possible from each state - start(), stop(), pause(), rewind()

    /**
     * PROBLEM : in each transition method, you have to detect state and then behave accordingly.
     * the detection logic is based of either switch or if conditionals, as the number of states grow, and state DEPENDENT behaviour grows
     * these methods become very large and complex.
     * <p>
     * How ?
     * <p>
     * Read through the below transition method, and consider if a new state was added in the enum, what would happen ?
     * you will have to add another if conditional in each of the transition method.
     * <p>
     * Solution
     * <p>
     * If you observe then root cause is that in each transition method i have to do something based on the current state,
     * I am using ifs to detect the state, and then execute the behaviour specific to that state.
     * Solution is to move these state dependent behaviour in their respective state classes and then switch these classes at runtime
     * using a common interface --> this is state pattern.
     */


    /////////////// FINITE TRANSITIONS ////////////////////
    public void start() {
        if (currentState == State.STARTED) {
            System.out.println("Already started");
        } else if (currentState == State.STOPPED || currentState == null) {
            System.out.println("player started");
            currentState = State.STARTED;
        } else if (currentState == State.REWINDING) {
            System.out.println("Player already running, please stop first");
        }
    }

    public void stop() {
        if (currentState == State.STARTED || currentState == null) {
            System.out.println("music player stopped");
            currentState = State.STOPPED;
        } else if (currentState == State.STOPPED) {
            System.out.println("Already stopped");
        } else if (currentState == State.REWINDING) {
            System.out.println("player stopped");
            currentState = State.STOPPED;
        }
    }

    public void rewind() {
        if (currentState == State.STARTED || currentState == null) {
            System.out.println("music player rewinding. already started");
            currentState = State.REWINDING;
        } else if (currentState == State.STOPPED) {
            System.out.println("rewinding");
            currentState = State.REWINDING;
        } else if (currentState == State.REWINDING) {
            System.out.println("music player already rewinding");
        }
    }

}

class MaintainablePlayer implements State {

    MaintainablePlayer() {
        System.out.println("MAINTAINABLE PLAYER CALLED");
    }

    //initial state
    State currentState = new Stopped(this);

    //delegate all transitions to
    @Override
    public void start() {
        // fire the transition impl of current state
        currentState.start();
    }

    @Override
    public void stop() {
        currentState.stop();
    }

    @Override
    public void rewind() {
        currentState.rewind();
    }
}

//state interface - all transitions
 interface State {
    void start();

    void stop();

    void rewind();
}

//all states as concrete classes.
 class Started implements State {
    MaintainablePlayer playerContext;
    Started(MaintainablePlayer playerContext) {
        this.playerContext = playerContext;
    }

    @Override
    public void start() {
        System.out.println("Already started");
    }

    @Override
    public void stop() {
        System.out.println("player stopped");
        playerContext.currentState = new Stopped(playerContext);
    }

    @Override
    public void rewind() {
        System.out.println("Player already running, please stop first");
    }
}

 class Stopped implements State {

     MaintainablePlayer playerContext;
     Stopped(MaintainablePlayer playerContext) {
         this.playerContext = playerContext;
     }

    @Override
    public void start() {
        System.out.println("player started");
        playerContext.currentState = new Started(playerContext);
    }

    @Override
    public void stop() {
        System.out.println("player already stopped");
    }

    @Override
    public void rewind() {
        System.out.println("player rewinding started");
        playerContext.currentState = new Rewinding(playerContext);
    }
}

 class Rewinding implements State {

     MaintainablePlayer playerContext;
     Rewinding(MaintainablePlayer playerContext) {
         this.playerContext = playerContext;
     }

    @Override
    public void start() {
        System.out.println("Player already running, please stop first");
    }

    @Override
    public void stop() {
        System.out.println("player stopped");
        playerContext.currentState = new Stopped(playerContext);
    }

    @Override
    public void rewind() {
        System.out.println("music player already rewinding");
    }
}