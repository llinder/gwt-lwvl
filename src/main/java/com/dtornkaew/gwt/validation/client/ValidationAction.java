package com.dtornkaew.gwt.validation.client;


public interface ValidationAction<T>
{
    public void invoke(ValidationResult result, T object);
    
    public void reset();
}
