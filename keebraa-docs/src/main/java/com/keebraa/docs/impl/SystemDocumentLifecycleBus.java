package com.keebraa.docs.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.keebraa.docs.DocumentLifecycleBus;
import com.keebraa.docs.exceptions.DocumentHandlingException;
import com.keebraa.docs.model.Document;
import com.keebraa.docs.model.DocumentState;
import com.keebraa.docs.model.dochandlers.DocumentLifecycleHandler;
import com.keebraa.docs.model.dochandlers.HandlingMethod;

public class SystemDocumentLifecycleBus implements DocumentLifecycleBus
{   
    private static SystemDocumentLifecycleBus singleton;

    public static DocumentLifecycleBus getInstance()
    {
        if (singleton == null)
        {
            singleton = new SystemDocumentLifecycleBus();
        }
        return singleton;
    }

    private Map<Class<? extends Document>, List<DocumentLifecycleHandler>> registeredHandlers;

    public SystemDocumentLifecycleBus()
    {
        registeredHandlers = new HashMap<Class<? extends Document>, List<DocumentLifecycleHandler>>();
    }

    public boolean handleDocument(Document document, DocumentState nextState)
            throws DocumentHandlingException
    {
        List<DocumentLifecycleHandler> handlers = getHandlers(document.getClass());
        for (DocumentLifecycleHandler handler : handlers)
        {
            List<Method> methods = getHandlingMethods(handler, nextState);
            for(Method method : methods)
            {
                handleByMethod(handler, method, document);
            }
        }
        return false;
    }

    private void handleByMethod(DocumentLifecycleHandler handler, Method method, Document document)
            throws DocumentHandlingException
    {
        try
        {
            method.invoke(handler, document);
        }
        catch (IllegalArgumentException e)
        {
            throw new DocumentHandlingException(e);
        }
        catch (IllegalAccessException e)
        {
            throw new DocumentHandlingException(e);
        }
        catch (InvocationTargetException e)
        {
            throw new DocumentHandlingException(e);
        }
    }

    public void registerDocumentLifecycleHandler(DocumentLifecycleHandler handler)
    {
        List<DocumentLifecycleHandler> handlers = getHandlers(handler.getHandlingClass());
        handlers.add(handler);
    }

    private List<Method> getHandlingMethods(DocumentLifecycleHandler handler,
                                            DocumentState nextState)
    {
        if (handler.getClass().isAnnotationPresent(HandlingMethod.class))
        {
            return new ArrayList<Method>(0);
        }
        Method[] methods = handler.getClass().getDeclaredMethods();
        List<Method> result = new ArrayList<Method>(methods.length);
        for (Method method : methods)
        {
            if (!method.isAnnotationPresent(HandlingMethod.class))
            {
                continue;
            }
            if (!method.getAnnotation(HandlingMethod.class).state().equals(nextState))
            {
                continue;
            }
            result.add(method);
        }
        return result;
    }

    private List<DocumentLifecycleHandler> getHandlers(Class<? extends Document> documentClass)
    {
        List<DocumentLifecycleHandler> handlers = registeredHandlers.get(documentClass);
        if (handlers == null)
        {
            handlers = new ArrayList<DocumentLifecycleHandler>();
            registeredHandlers.put(documentClass, handlers);
        }
        return handlers;
    }
}
