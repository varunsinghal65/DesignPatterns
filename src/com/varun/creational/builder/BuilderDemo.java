package com.varun.creational.builder;

/**
 * Use when you have many params (some are required, some are optional) for object creation.
 * <p>
 * Main advantage : scalable with number of params and easy ot read, minimizes risk of passing wrong
 * values due to successive params having same types
 * check highlighted points from effective java 3rd edition
 */

public class BuilderDemo {

    public static void main(String[] args) {
        NutritionFacts nf = new NutritionFacts.Builder(1, 2)
                .fat(3)
                .sodium(4)
                .chlorine(5)
                .spices(6)
                .build();
        System.out.println(nf);
    }

    private static class NutritionFacts {
        //required
        int servingSize;
        int numberOfServings;
        //optional
        int fat;
        int sodium;
        int chlorine;
        int spices;

        @Override
        public String toString() {
            return "NutritionFacts{" +
                    "servingSize=" + servingSize +
                    ", numberOfServings=" + numberOfServings +
                    ", fat=" + fat +
                    ", sodium=" + sodium +
                    ", chlorine=" + chlorine +
                    ", spices=" + spices +
                    '}';
        }

        public static class Builder {
            //required
            int servingSize;
            int numberOfServings;
            //optional
            int fat = 0;
            int sodium = 0;
            int chlorine = 0;
            int spices = 0;

            public Builder(int servingSize, int numberOfServings) {
                this.servingSize = servingSize;
                this.numberOfServings = numberOfServings;
            }

            public Builder fat(int fat) {
                this.fat = fat;
                return this;
            }

            public Builder sodium(int sodium) {
                this.sodium = sodium;
                return this;
            }

            public Builder chlorine(int chlorine) {
                this.chlorine = chlorine;
                return this;
            }

            public Builder spices(int spices) {
                this.spices = spices;
                return this;
            }

            public NutritionFacts build() {
                return new NutritionFacts(this);
            }
        }

        public NutritionFacts(Builder builder) {
            this.servingSize = builder.servingSize;
            this.numberOfServings = builder.numberOfServings;
            this.fat = builder.fat;
            this.sodium = builder.sodium;
            this.chlorine = builder.chlorine;
            this.spices = builder.spices;
        }
    }
}
