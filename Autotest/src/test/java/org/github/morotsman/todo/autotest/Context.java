package org.github.morotsman.todo.autotest;


import cucumber.annotation.After;
import cucumber.annotation.Before;

public class Context {

    @Before("@TestContext")
    public void setup(){
        System.out.println("Initiating test context.");
    }

    @After("@TestContext")
    public void tearDown(){
        System.out.println("Cleaning up test context.");
    }

}

