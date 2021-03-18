package com.training.singleton;

public class SingletonService {

    private static SingletonService instance;

    private SingletonService(){
        System.out.println("singleton initialized");
    }

    public static SingletonService getInstance(){
        if(instance == null)
            instance = new SingletonService();
        return instance;
    }
}
