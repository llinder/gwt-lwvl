package com.dtornkaew.gwt.validation.client.validators;

import java.util.NoSuchElementException;

import com.dtornkaew.gwt.validation.client.ValidationResult;
import com.dtornkaew.gwt.validation.client.Validator;
import com.dtornkaew.gwt.validation.client.i18n.MessageProvider;
import com.dtornkaew.gwt.validation.client.validators.RequiredValidator.ErrorCodes;
import com.dtornkaew.gwt.validation.client.validators.RequiredValidator.RequiredMessageBundle;
import com.google.gwt.i18n.client.Messages;
import com.google.gwt.user.client.ui.HasValue;

public class RequiredValidator<T>
    extends Validator<T>
{
    private final MessageProvider<ErrorCodes> messageProvider;

    private boolean required = true;

    public RequiredValidator( T target, Messages ... messages )
    {
        super( target, messages );
        messageProvider = new RequiredMessageProvider( this, messages );
    }

    @Override
    public ValidationResult validate()
    {
        final ValidationResult result = super.validate();

        final Object o = getValue();

        if ( required && ( o == null || "".equals( o ) ) )
            result.addError( result.new ValidationError<ErrorCodes>( ErrorCodes.REQUIRED, messageProvider ) );

        return result;
    }

    @Override
    protected Object getValue()
    {
        if( target instanceof HasValue<?> )
            return ((HasValue<?>)target).getValue();
        else
            throw new IllegalStateException( "Unsupported target type. Only target that implement HasValue are accepted." );
    }

    public Validator<T> setRequired( boolean value )
    {
        required = value;
        return this;
    }

    public boolean getRequired()
    {
        return required;
    }

    public static interface RequiredMessageBundle
        extends Messages
    {
        @DefaultMessage( "Required" )
        @Key( "validations_required_required" )
        public String validations_required_required();
    }

    public static enum ErrorCodes
    {
        REQUIRED;
    }

}

class RequiredMessageProvider
    implements MessageProvider<ErrorCodes>
{
    private final RequiredMessageBundle bundle;

    @SuppressWarnings( "unused" )
    private final Validator<?> validator;

    RequiredMessageProvider( Validator<?> validator, Messages[] messages )
    {
        this.bundle = getBundle( messages );
        this.validator = validator;
    }

    // Can't find way to make this a generic lookup and still work with GWT
    private RequiredMessageBundle getBundle( Messages[] messages )
    {
        for ( Messages m : messages )
            if ( m instanceof RequiredMessageBundle )
                return (RequiredMessageBundle) m;

        throw new NoSuchElementException( "Message bundle, BaseMessageBundle, doesn't exist." );
    }

    public String getMessage( ErrorCodes code )
    {
        switch ( code )
        {
            case REQUIRED:
                return bundle.validations_required_required();
            default:
                return "";
        }
    }
}
