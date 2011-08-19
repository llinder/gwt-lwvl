package com.dtornkaew.gwt.validation.client.validators;

import com.dtornkaew.gwt.validation.client.ValidationResult;
import com.dtornkaew.gwt.validation.client.Validator;
import com.dtornkaew.gwt.validation.client.i18n.MessageProvider;
import com.dtornkaew.gwt.validation.client.validators.NumberValidator.ErrorCodes;
import com.dtornkaew.gwt.validation.client.validators.NumberValidator.NumberMessageBundle;
import com.google.gwt.i18n.client.Messages;
import com.google.gwt.user.client.ui.HasValue;

public class NumberValidator
    extends Validator
{
    private final HasValue<String> target;
    
    double minValue = 0;
    
    double maxValue = 0;
    
    private final MessageProvider<ErrorCodes> messageProvider;
    
    public NumberValidator( NumberMessageBundle bundle, HasValue<String> target )
    {
        this.target = target;
        messageProvider = new NumberMessageProvider( this, bundle );
    }
    
    public void setMinValue( double min )
    {
        minValue = min;
    }
    public double getMinValue()
    {
        return minValue;
    }
    
    public void setMaxValue( double max )
    {
        maxValue = max;
    }
    public double getMaxValue()
    {
        return maxValue;
    }

    @Override
    public ValidationResult validate()
    {
        final Double d = Double.valueOf( target.getValue() );
        
        final ValidationResult result = new ValidationResult( this );
        
        if( d < minValue )
        {
            result.addError( result.new ValidationError<ErrorCodes>( ErrorCodes.LOWER_THAN_MIN, messageProvider ) );
        }
        else if( d > maxValue )
        {
            result.addError( result.new ValidationError<ErrorCodes>( ErrorCodes.EXCEEDS_MAX, messageProvider ) );
        }
        
        return result;
    }
    
    public static interface NumberMessageBundle extends Messages
    {
        public String number_lowerThanMin( String min, String max );
        public String number_exceedsMax( String min, String max );
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
    
    private final NumberValidator validator;
    
    NumberMessageProvider( NumberValidator validator, NumberMessageBundle bundle )
    {
        this.bundle = bundle;
        this.validator = validator;
    }
    
    public String getMessage( ErrorCodes code )
    {
        switch( code )
        {
            case LOWER_THAN_MIN:
                return bundle.number_lowerThanMin( String.valueOf( validator.minValue ), String.valueOf( validator.maxValue ) );
            case EXCEEDS_MAX:
                return bundle.number_exceedsMax( String.valueOf( validator.minValue ), String.valueOf( validator.maxValue ) );
            default:
                return "";
        }
    }
}
