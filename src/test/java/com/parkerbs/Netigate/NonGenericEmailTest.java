package com.parkerbs.Netigate;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class NonGenericEmailTest {
    
    @Before
    public void setUp(){
        RemoveEmails.init();
    }

    @Test
    public void TestNonGenericEmail(){
        assertEquals(true, RemoveEmails.checkEmail("ryan.clark@gmail.com"));
    }


}