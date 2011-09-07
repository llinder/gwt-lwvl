package com.dtornkaew.gwt.validation.client;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.google.gwt.i18n.client.ConstantsWithLookup;

public abstract class Validator<T>
{
    public static final String DEFAULT_PREFIX = "validation_";
    public static final String DEFAULT_KEY = "key";
    
    private List<ValidationAction> actions = new LinkedList<ValidationAction>();

    protected final T target;

    private boolean enabled;
    
    private final String key;
    
    public Validator( String key, T target )
    {
        this.target = target;
        this.enabled = true;
        this.key = key;
    }
    
    public String getKey()
    {
        return key;
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

    public interface ValidationMessageBundle extends ConstantsWithLookup
    {
        
    }
    
    public interface HasValue<T>
    {
        public T getValue();
    }
}
