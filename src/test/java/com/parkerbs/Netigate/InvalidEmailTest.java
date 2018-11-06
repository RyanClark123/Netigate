package com.parkerbs.Netigate;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class InvalidEmailTest {

    @Before
    public void setUp(){
        ConfigFile.setMessage(false);
        RemoveEmails.init();
    }

    @Test
    public void RunTest(){
        assertEquals(false, RemoveEmails.checkEmail("asodiyweiruywer"));
    }

}