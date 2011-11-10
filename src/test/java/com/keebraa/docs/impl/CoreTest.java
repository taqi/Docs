package com.keebraa.docs.impl;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.keebraa.docs.Core;
import com.keebraa.docs.DocumentLifecycleBus;

public class CoreTest
{

    @Test
    public void getDocumentLifecycleBus_NormalCase() throws Exception
    {
        DocumentLifecycleBus bus = Core.getInstance().getDocumentLifecycleBus();
        
        assertNotNull(Core.getInstance());
        assertNotNull(bus);
    }
}
