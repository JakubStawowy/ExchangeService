package com.example.demo.entities;

public class Exchange {
    private float value;

    public Exchange(float value) {
        this.value = value;
    }

    public Exchange() {
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}
