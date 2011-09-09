package com.dtornkaew.gwt.validation.client.validators;

import com.dtornkaew.gwt.validation.client.ValidationResult;
import com.dtornkaew.gwt.validation.client.Validator;
import com.dtornkaew.gwt.validation.client.Validator.ValidationMessageBundle;
import com.dtornkaew.gwt.validation.client.i18n.MessageProvider;
import com.dtornkaew.gwt.validation.client.validators.RequiredValidator.ErrorCodes;

public class RequiredValidator<T>
    extends Validator<T>
{
    private final MessageProvider<ErrorCodes> messageProvider;
    
    private boolean required = true;
    
    public RequiredValidator( T target, ValidationMessageBundle bundle )
    {
        this( DEFAULT_PREFIX, DEFAULT_KEY, target, bundle );
    }
    
    public RequiredValidator( String prefix, T target, ValidationMessageBundle bundle )
    {
        this( prefix, DEFAULT_KEY, target, bundle );
    }
    
    public RequiredValidator( String prefix, String key, T target, ValidationMessageBundle bundle )
    {
        super( key, target );
        messageProvider = new RequiredMessageProvider( prefix, this, bundle );
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
            return ((Validator.HasValue<?>)target).getValue();
        if( target instanceof com.google.gwt.user.client.ui.HasValue<?> )
            return ((com.google.gwt.user.client.ui.HasValue<?>)target).getValue();
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

    public static enum ErrorCodes
    {
        REQUIRED;
    }

}

class RequiredMessageProvider
    implements MessageProvider<ErrorCodes>
{
    private final ValidationMessageBundle bundle;
    
    private final String prefix;

    @SuppressWarnings( "unused" )
    private final Validator<?> validator;
    
    RequiredMessageProvider( Validator<?> validator, ValidationMessageBundle bundle )
    {
        this( Validator.DEFAULT_PREFIX, validator, bundle );
    }
    
    RequiredMessageProvider( String prefix,  Validator<?> validator, ValidationMessageBundle bundle )
    {
        this.prefix = prefix;
        this.bundle = bundle;
        this.validator = validator;
    }

    public String getMessage( ErrorCodes code )
    {
        switch ( code )
        {
            case REQUIRED:
                return bundle.getString( prefix+"required_required" );
            default:
                return "";
        }
    }
}
