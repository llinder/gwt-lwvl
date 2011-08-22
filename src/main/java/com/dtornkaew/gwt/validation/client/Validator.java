package com.dtornkaew.gwt.validation.client;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.google.gwt.i18n.client.Messages;

public abstract class Validator<T>
{
    private List<ValidationAction> actions = new LinkedList<ValidationAction>();

    protected final T target;

    private boolean enabled;
    
    public Validator( T target, Messages[] messages )
    {
        this.target = target;
        this.enabled = true;
    }
    
    public boolean isEnabled()
    {
        return enabled;
    }
    
    public Validator<T> setEnabled( boolean value )
    {
        enabled = value;
        return this;
    }

    public ValidationResult validate()
    {
        final ValidationResult result = new ValidationResult( this );

        return result;
    }

    public Validator<T> addAction( ValidationAction action )
    {
        actions.add( action );
        return this;
    }

    public void performActions( ValidationResult result )
    {
        Iterator<ValidationAction> i = actions.iterator();
        while ( i.hasNext() )
            i.next().invoke( result );
    }

    public void resetActions()
    {
        Iterator<ValidationAction> i = actions.iterator();
        while ( i.hasNext() )
            i.next().reset();
    }

    protected abstract Object getValue();

}
