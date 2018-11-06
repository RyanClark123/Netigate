package com.parkerbs.Netigate;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GenericEmailTest{
    
    @Before
    public void setUp(){
        ConfigFile.setMessage(false);
        RemoveEmails.init();        
    }

    @Test
    public void TestGenericEmail(){
        assertEquals(false, RemoveEmails.checkEmail("info@gmail.com"));
    }
}

