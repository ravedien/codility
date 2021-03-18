package com.training.singleton;

public class SingletonRunner {
    public static void main(String[] args) {
        SingletonService.getInstance();
        SingletonService.getInstance();
    }
}
