package com.varun.structural;

/**
 * https://www.vogella.com/tutorials/DesignPatternAdapter/article.html
 * https://refactoring.guru/design-patterns/adapter
 *  The adapter pattern describes how to convert an object into another object which a clients expects.
 *  This pattern mainly adapts one object to another one.
 *  Adapters allow objects to work together that couldn’t otherwise because of incompatible interfaces
 *
 */

public class Adapter {

    public static void main(String[] args) {
        IndiaElectricityPlug indianPlug = ()->System.out.println("Electricity provided from indian plug");
        UKElectricityPlug ukPlug = new IndianToUkPlugAdaptor(indianPlug);
        UKElectricitySocket ukSocket = new UKElectricitySocket();
        ukSocket.plugIn(ukPlug);
    }

    private static class IndianToUkPlugAdaptor implements UKElectricityPlug {

        IndiaElectricityPlug indianPlug = null;

        IndianToUkPlugAdaptor(IndiaElectricityPlug plug) {
            this.indianPlug = plug;
        }

        @Override
        public void giveElectricity() {
            indianPlug.provideElectricity();
        }
    }

    private static class UKElectricitySocket {

        public void plugIn(UKElectricityPlug plug) {
            plug.giveElectricity();
        }

    }

    private interface UKElectricityPlug {
        void giveElectricity();
    }

    private interface IndiaElectricityPlug {
        void provideElectricity();
    }

}
