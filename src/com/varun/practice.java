package com.varun;

class Driver {
    public static void main(String[] args) {
        String str = new NutritionFacts.Builder(2, 4)
                .withCalcium(6)
                .withMagnesium(7)
                .withSodium(10)
                .build()
                .toString();
        System.out.println(str);
    }
}

class NutritionFacts {
    //optional
    int sodium;
    int magnesium;
    int calcium;
    //mandatory
    int servingsSize;
    int numberOfServings;

    private NutritionFacts(Builder builder) {
        this.sodium = builder.sodium;
        this.magnesium = builder.magnesium;
        this.calcium = builder.calcium;
        this.servingsSize = builder.servingsSize;
        this.numberOfServings = builder.numberOfServings;
    }

    public static class Builder {
        //optional
        int sodium;
        int magnesium;
        int calcium;
        //mandatory
        int servingsSize;
        int numberOfServings;

        Builder(int servingsSize, int numberOfServings) {
            this.servingsSize = servingsSize;
            this.numberOfServings = numberOfServings;
        }

        public Builder withSodium(int amount) {
            this.sodium = amount;
            return this;
        }

        public Builder withMagnesium(int amount) {
            this.magnesium = amount;
            return this;
        }

        public Builder withCalcium(int amount) {
            this.calcium = amount;
            return this;
        }

        public NutritionFacts build() {
            return new NutritionFacts(this);
        }
    }

    @Override
    public String toString() {
        return "NutritionFacts{" +
                "sodium=" + sodium +
                ", magnesium=" + magnesium +
                ", calcium=" + calcium +
                ", servingsSize=" + servingsSize +
                ", numberOfServings=" + numberOfServings +
                '}';
    }
}