package com.keebraa.docs.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.keebraa.docs.DocumentLifecycleBus;
import com.keebraa.docs.exceptions.DocumentHandlingException;
import com.keebraa.docs.exceptions.DocumentLifecycleHandlerException;
import com.keebraa.docs.model.Document;
import com.keebraa.docs.model.DocumentState;
import com.keebraa.docs.model.dochandlers.DocumentStateHandler;
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

    private Map<Type, Set<Object>> registeredHandlers;

    public SystemDocumentLifecycleBus()
    {
        registeredHandlers = new HashMap<Type, Set<Object>>();
    }

    public void handleDocument(Document document, DocumentState nextState)
            throws DocumentHandlingException
    {
        Set<Object> handlers = getHandlers(document.getClass());
        for (Object handler : handlers)
        {
            List<Method> methods = getHandlingMethods(handler, nextState);
            for (Method method : methods)
            {
                handleByMethod(handler, method, document);
            }
        }
    }

    private void handleByMethod(Object handler, Method method,
                                Document document) throws DocumentHandlingException
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

    public void registerDocumentLifecycleHandler(Object handler)
            throws DocumentLifecycleHandlerException
    {
        if (!handler.getClass().isAnnotationPresent(DocumentStateHandler.class))
        {
            throw new DocumentLifecycleHandlerException("Class "
                    + handler.getClass().getName() + " has not been anotated as handler.");
        }
        Class<?> clazz = handler.getClass().getAnnotation(DocumentStateHandler.class).handledClass();
        if (!Document.class.isAssignableFrom(clazz))
        {
            throw new DocumentLifecycleHandlerException(
                    "Handler has been anotated on wrong Document realization.");
        }
        Set<Object> handlers = getHandlers(clazz);
        handlers.add(handler);
    }

    private List<Method> getHandlingMethods(Object handler,
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
            if (!method.getAnnotation(HandlingMethod.class).state().equals(nextState.getStateCaption()))
            {
                continue;
            }
            result.add(method);
        }
        return result;
    }

    private Set<Object> getHandlers(Type documentClass)
    {
        Set<Object> handlers = registeredHandlers.get(documentClass);
        if (handlers == null)
        {
            handlers = new HashSet<Object>();
            registeredHandlers.put(documentClass, handlers);
        }
        return handlers;
    }
}
