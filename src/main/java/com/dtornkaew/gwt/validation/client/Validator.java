package com.dtornkaew.gwt.validation.client;

import java.util.LinkedList;
import java.util.List;

public abstract class Validator
{
    protected List<ValidationAction<?>> actions = new LinkedList<ValidationAction<?>>();
    
    private boolean required = true;
    
    public boolean getRequired()
    {
        return required;
    }
    public void setRequired( boolean value )
    {
        required = value;
    }
}
