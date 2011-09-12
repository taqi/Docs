package com.keebraa.docs;

public class ApplicationContext
{   
    private static Core singleton;
    
    public static Core getInstance()
    {
        if(singleton == null)
        {
            singleton = initializeApplicationContext();
            
        }
        return singleton;
    }
    
    private static Core initializeApplicationContext()
    {
        Core result = new Core();
        
        return result;
    }
}
