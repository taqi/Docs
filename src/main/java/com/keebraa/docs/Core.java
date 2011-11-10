package com.keebraa.docs;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Core
{   
    private static Core singleton;
    
    private DocumentLifecycleBus documentLifecycleBus;
    
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
        ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        DocumentLifecycleBus bus = (DocumentLifecycleBus)context.getBean(DocumentLifecycleBus.LIFECYCLE_BUS_BEANNAME);
        result.setDocumentLifecycleBus(bus);
        return result;
    }
    
    private void setDocumentLifecycleBus(DocumentLifecycleBus documentLifecycleBus)
    {
        this.documentLifecycleBus = documentLifecycleBus;
    }
    
    public DocumentLifecycleBus getDocumentLifecycleBus()
    {
        return documentLifecycleBus;
    }
}
