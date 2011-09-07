package com.dtornkaew.gwt.validation.client.validators;

import java.text.MessageFormat;

import com.dtornkaew.gwt.validation.client.ValidationResult;
import com.dtornkaew.gwt.validation.client.Validator;
import com.dtornkaew.gwt.validation.client.Validator.HasValue;
import com.dtornkaew.gwt.validation.client.Validator.ValidationMessageBundle;
import com.dtornkaew.gwt.validation.client.i18n.MessageProvider;
import com.dtornkaew.gwt.validation.client.validators.NumberValidator.ErrorCodes;
import com.google.gwt.i18n.client.Messages;

public class NumberValidator<N>
    extends RequiredValidator<HasValue<N>>
{    
    N minValue;
    
    N maxValue;
    
    private final MessageProvider<ErrorCodes> messageProvider;
    
    public NumberValidator( HasValue<N> target, ValidationMessageBundle bundle )
    {
        this( DEFAULT_PREFIX, DEFAULT_KEY, target, bundle );
    }
    
    public NumberValidator( String prefix, HasValue<N> target, ValidationMessageBundle bundle )
    {
        this( prefix, DEFAULT_KEY, target, bundle );
    }
    
    public NumberValidator( String prefix, String key, HasValue<N> target, ValidationMessageBundle bundle )
    {
        super( prefix, key, target, bundle );
        messageProvider = new NumberMessageProvider( prefix, this, bundle );
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
    private final ValidationMessageBundle bundle;
    
    private final String prefix;
    
    private final NumberValidator<?> validator;
    
    NumberMessageProvider( NumberValidator<?> validator, ValidationMessageBundle bundle )
    {
        this( Validator.DEFAULT_PREFIX, validator, bundle );
    }
    
    NumberMessageProvider( String prefix, NumberValidator<?> validator, ValidationMessageBundle bundle )
    {
        this.prefix = prefix;
        this.bundle = bundle;
        this.validator = validator;
    }
    
    public String getMessage( ErrorCodes code )
    {       
        String message = null;
        
        switch( code )
        {
            case LOWER_THAN_MIN:
                message = bundle.getString( prefix+"number_lowerThanMin" );
                break;
            case EXCEEDS_MAX:
                message = bundle.getString( prefix+"number_exceedsMax" );
                break;
        }
        
        if( message != null )
            return MessageFormat.format( message, validator.minValue, validator.maxValue );
        else
            return "";
    }
}
