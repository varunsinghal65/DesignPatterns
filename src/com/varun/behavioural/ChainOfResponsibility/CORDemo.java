package com.varun.behavioural.ChainOfResponsibility;

/**
 * https://www.journaldev.com/1617/chain-of-responsibility-design-pattern-in-java#chain-of-responsibility-design-pattern
 * Real world example:
 * An ATM can dispense 50, 10, 1 INR denominations
 * Now, for it to achieve that, its has to first find number of note of 50,
 * then number of notes of 10, finally number of notes of 1.
 * So the the client request (ATM.dispense(1234)) basically will move through 3 processors.
 *
 * READ APPLICABILITY :
 * https://refactoring.guru/design-patterns/chain-of-responsibility#:~:text=Use%20the%20pattern%20when%20it's,supposed%20to%20change%20at%20runtime.
 *
 * COR pattern allows
 * - to add handlers at RT
 * - allows to change sequence of handlers
 * - all this with no impact to client.
 */
public class CORDemo {

    public static void main(String[] args) {
        ATM.dispense(1234);
    }

    private static class ATM {
        public static void dispense(int amountToBeDispensed) {
            //init the processors
            DispenseChainProcessor note50Dispenser = new Note50Dispenser();
            DispenseChainProcessor note10Dispenser = new Note10Dispenser();
            DispenseChainProcessor note1Dispenser = new Note1Dispenser();

            //set the processors execution sequence
            note50Dispenser.setNextProcessor(note10Dispenser);
            note10Dispenser.setNextProcessor(note1Dispenser);
            note1Dispenser.setNextProcessor(null);

            //start the execution by firing the first processor in the chain
            note50Dispenser.dispense(amountToBeDispensed);
        }
    }

    private static interface DispenseChainProcessor {
        void setNextProcessor(DispenseChainProcessor nextProcessor);

        void dispense(int amount);
    }

    private static class Note50Dispenser implements DispenseChainProcessor {
        private DispenseChainProcessor nextProcessor = null;

        @Override
        public void setNextProcessor(DispenseChainProcessor nextProcessor) {
            this.nextProcessor = nextProcessor;
        }

        @Override
        public void dispense(int amountToBeDispensed) {
            if (amountToBeDispensed < 50) {
                nextProcessor.dispense(amountToBeDispensed);
            } else {
                int numberOfNotes = amountToBeDispensed / 50;
                int remainingAmountToBeDispensed = amountToBeDispensed % 50;
                System.out.println(numberOfNotes + " notes of 50 dispensed");
                nextProcessor.dispense(remainingAmountToBeDispensed);
            }
        }
    }

    private static class Note10Dispenser implements DispenseChainProcessor {

        private DispenseChainProcessor nextProcessor = null;

        @Override
        public void setNextProcessor(DispenseChainProcessor nextProcessor) {
            this.nextProcessor = nextProcessor;
        }

        @Override
        public void dispense(int amountToBeDispensed) {
            if (amountToBeDispensed < 10) {
                nextProcessor.dispense(amountToBeDispensed);
            } else {
                int numberOfNotes = amountToBeDispensed / 10;
                int remainingAmountToBeDispensed = amountToBeDispensed % 10;
                System.out.println(numberOfNotes + " notes of 10 dispensed");
                nextProcessor.dispense(remainingAmountToBeDispensed);
            }
        }
    }

    private static class Note1Dispenser implements DispenseChainProcessor {

        private DispenseChainProcessor nextProcessor = null;

        @Override
        public void setNextProcessor(DispenseChainProcessor nextProcessor) {
            this.nextProcessor = nextProcessor;
        }

        @Override
        public void dispense(int amountToBeDispensed) {
            if (amountToBeDispensed > 0) {
                int numberOfNotes = amountToBeDispensed;
                System.out.println(numberOfNotes + " notes of 1 dispensed");
            }
        }
    }

}
