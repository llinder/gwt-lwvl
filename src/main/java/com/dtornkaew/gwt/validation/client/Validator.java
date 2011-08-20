package com.dtornkaew.gwt.validation.client;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import com.dtornkaew.gwt.validation.client.Validator.BaseMessageBundle;
import com.dtornkaew.gwt.validation.client.Validator.ErrorCodes;
import com.dtornkaew.gwt.validation.client.i18n.MessageProvider;
import com.google.gwt.i18n.client.Messages;

public abstract class Validator<T>
{
    private List<ValidationAction> actions = new LinkedList<ValidationAction>();

    private boolean required = true;

    protected final T target;

    private final MessageProvider<ErrorCodes> messageProvider;

    public Validator( T target, Messages[] messages )
    {
        this.target = target;
        messageProvider = new BaseMessageProvider( this, messages );
    }

    public boolean getRequired()
    {
        return required;
    }

    public Validator<T> setRequired( boolean value )
    {
        required = value;
        return this;
    }

    public ValidationResult validate()
    {
        final ValidationResult result = new ValidationResult( this );

        final Object o = getValue();

        if ( required && ( o == null || "".equals( o ) ) )
            result.addError( result.new ValidationError<ErrorCodes>( ErrorCodes.REQUIRED, messageProvider ) );

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
        while( i.hasNext() )
            i.next().invoke( result );
    }
    
    public void resetActions()
    {
        Iterator<ValidationAction> i = actions.iterator();
        while( i.hasNext() )
            i.next().reset();
    }

    protected abstract Object getValue();
    
    
    
    
    
    
    

    public static interface BaseMessageBundle
        extends Messages
    {
        @DefaultMessage("Required")
        @Key("validations_base_required")
        public String validations_base_required();
    }

    public static enum ErrorCodes
    {
        REQUIRED;
    }
}

class BaseMessageProvider
    implements MessageProvider<ErrorCodes>
{
    private final BaseMessageBundle bundle;

    @SuppressWarnings( "unused" )
    private final Validator<?> validator;

    BaseMessageProvider( Validator<?> validator, Messages[] messages )
    {
        this.bundle = getBundle( messages );
        this.validator = validator;
    }

    // Can't find way to make this a generic lookup and still work with GWT
    private BaseMessageBundle getBundle( Messages[] messages )
    {
        for ( Messages m : messages )
            if ( m instanceof BaseMessageBundle )
                return (BaseMessageBundle) m;

        throw new NoSuchElementException( "Message bundle, BaseMessageBundle, doesn't exist." );
    }

    public String getMessage( ErrorCodes code )
    {
        switch ( code )
        {
            case REQUIRED:
                return bundle.validations_base_required();
            default:
                return "";
        }
    }
}
