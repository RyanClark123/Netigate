package com.parkerbs.Netigate;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ValidEmailTest {

    @Before
    public void setUp(){
        ConfigFile.setMessage(false);
        RemoveEmails.init();
    }

    @Test
    public void runTest(){
        assertEquals(true, RemoveEmails.checkEmail("test@gmail.com"));
    }

}