package com.CorrelApp.demo.classesMath;

public class Result {

        private SuperiorData firstElem;
        private SuperiorData secondElem;
        private double r;

        public Result(SuperiorData firstElem, SuperiorData secondElem, double r) {
            this.firstElem = firstElem;
            this.secondElem = secondElem;
            this.r = r;
        }

        public SuperiorData getFirstElem() {
            return firstElem;
        }

        public void setFirstElem(SuperiorData firstElem) {
            this.firstElem = firstElem;
        }

        public SuperiorData getSecondElem() {
            return secondElem;
        }

        public void setSecondElem(SuperiorData secondElem) {
            this.secondElem = secondElem;
        }

        public double getR() {
            return r;
        }

        public void setR(double r) {
            this.r = r;
        }
    }

