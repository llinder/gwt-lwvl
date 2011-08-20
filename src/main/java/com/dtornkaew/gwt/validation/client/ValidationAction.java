package com.dtornkaew.gwt.validation.client;


public interface ValidationAction
{    
    public void invoke(ValidationResult ... results);
    
    public void reset();
}
