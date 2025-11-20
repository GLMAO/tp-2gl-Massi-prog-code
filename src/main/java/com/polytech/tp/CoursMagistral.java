package com.polytech.tp;

public class CoursMagistral extends CoursDecorator {
    public CoursMagistral(ICours cours) {
        super(cours);
    }

    @Override
    public String getDescription() {
        return coursDecorated.getDescription() + " (Magistral)";
    }

    @Override
    public double getDuree() {
        // Les cours magistraux peuvent être plus longs
        return coursDecorated.getDuree() + 0.5;
    }
}