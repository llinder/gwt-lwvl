package com.dtornkaew.gwt.validation.client.validators;

import java.util.NoSuchElementException;

import com.dtornkaew.gwt.validation.client.ValidationResult;
import com.dtornkaew.gwt.validation.client.i18n.MessageProvider;
import com.dtornkaew.gwt.validation.client.validators.NumberValidator.ErrorCodes;
import com.dtornkaew.gwt.validation.client.validators.NumberValidator.NumberMessageBundle;
import com.google.gwt.i18n.client.Messages;
import com.google.gwt.user.client.ui.HasValue;

public class NumberValidator<N>
    extends RequiredValidator<HasValue<?>>
{    
    N minValue;
    
    N maxValue;
    
    private final MessageProvider<ErrorCodes> messageProvider;
    
    public NumberValidator( HasValue<?> target, Messages ... messages )
    {
        super( target, messages );
        messageProvider = new NumberMessageProvider( this, messages );
    }
    
    public NumberValidator<N> setMinValue( N min )
    {
        minValue = min;
        return this;
    }
    public N getMinValue()
    {
        return minValue;
    }
    
    public NumberValidator<N> setMaxValue( N max )
    {
        maxValue = max;
        return this;
    }
    public N getMaxValue()
    {
        return maxValue;
    }

    @Override
    public ValidationResult validate()
    {
        final ValidationResult result = super.validate();
        
        if( result.getErrors().size() == 0 )
        {
            String v = String.valueOf( getValue() );
            if( v == null || "".equals( v ) )
                v = "0";
            
            if( minValue instanceof Double )
            {
                final Double d = Double.valueOf( v );
                if( minValue != null && d < (Double)minValue )
                    result.addError( result.new ValidationError<ErrorCodes>( ErrorCodes.LOWER_THAN_MIN, messageProvider ) );
                else if( maxValue != null && d > (Double)maxValue )
                    result.addError( result.new ValidationError<ErrorCodes>( ErrorCodes.EXCEEDS_MAX, messageProvider ) );
            }
            else if( minValue instanceof Integer )
            {
                final Integer i = Integer.valueOf( v );
                if( minValue != null && i < (Integer)minValue )
                    result.addError( result.new ValidationError<ErrorCodes>( ErrorCodes.LOWER_THAN_MIN, messageProvider ) );
                else if( maxValue != null && i > (Integer)maxValue )
                    result.addError( result.new ValidationError<ErrorCodes>( ErrorCodes.EXCEEDS_MAX, messageProvider ) );
            }
            else if( minValue instanceof Float )
            {
                final Float f = Float.valueOf( v );
                if( minValue != null && f < (Float)minValue )
                    result.addError( result.new ValidationError<ErrorCodes>( ErrorCodes.LOWER_THAN_MIN, messageProvider ) );
                else if( maxValue != null && f > (Float)maxValue )
                    result.addError( result.new ValidationError<ErrorCodes>( ErrorCodes.EXCEEDS_MAX, messageProvider ) );
            }
            else if( minValue instanceof Long )
            {
                final Long l = Long.valueOf( v );
                if( minValue != null && l < (Long)minValue )
                    result.addError( result.new ValidationError<ErrorCodes>( ErrorCodes.LOWER_THAN_MIN, messageProvider ) );
                else if( maxValue != null && l > (Long)maxValue )
                    result.addError( result.new ValidationError<ErrorCodes>( ErrorCodes.EXCEEDS_MAX, messageProvider ) );
            }
        }
        
        return result;
    }
    
    @Override
    protected Object getValue()
    {
        return target.getValue();
    }
    
    public static interface NumberMessageBundle extends Messages
    {
        @DefaultMessage("Lower than minimum")
        @Key("validation_number_lowerThanMin")
        public String validation_number_lowerThanMin( String min, String max );
        @DefaultMessage("Exceeds maximum")
        @Key("validation_number_exceedsMax")
        public String validation_number_exceedsMax( String min, String max );
    }
    
    public static enum ErrorCodes
    {
        LOWER_THAN_MIN,
        EXCEEDS_MAX,
        NEGATIVE;
    }
}

class NumberMessageProvider implements MessageProvider<ErrorCodes>
{
    private final NumberMessageBundle bundle;
    
    private final NumberValidator<?> validator;
    
    NumberMessageProvider( NumberValidator<?> validator, Messages[] messages )
    {
        this.bundle = getBundle( messages );
        this.validator = validator;
    }
    
    // Can't find way to make this a generic lookup and still work with GWT
    private NumberMessageBundle getBundle( Messages[] messages )
    {
        for( Messages m : messages )
            if( m instanceof NumberMessageBundle )
                return (NumberMessageBundle)m;
     
        throw new NoSuchElementException( "Message bundle, DoubleMessageBundle, doesn't exist." );
    }
          
    
    public String getMessage( ErrorCodes code )
    {
        switch( code )
        {
            case LOWER_THAN_MIN:
                return bundle.validation_number_lowerThanMin( String.valueOf( validator.minValue ), String.valueOf( validator.maxValue ) );
            case EXCEEDS_MAX:
                return bundle.validation_number_exceedsMax( String.valueOf( validator.minValue ), String.valueOf( validator.maxValue ) );
            default:
                return "";
        }
    }
}
