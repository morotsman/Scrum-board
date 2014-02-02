package org.github.morotsman.todo.autotest;


import cucumber.annotation.After;
import cucumber.annotation.Before;
import org.github.morotsman.todo.autotest.util.TestContext;

public class Context {


    @Before("@TestContext")
    public void setup(){
        System.out.println("Initiating test context.");

    }

    @After("@TestContext")
    public void tearDown(){
        TestContext.instance().cleanUp();
    }

}

