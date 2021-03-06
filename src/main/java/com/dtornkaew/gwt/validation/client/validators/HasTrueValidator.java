package com.dtornkaew.gwt.validation.client.validators;

import com.dtornkaew.gwt.validation.client.Validator.HasValue;

public class HasTrueValidator
    extends RequiredValidator<HasValue<Boolean>[]>
{
    public HasTrueValidator( HasValue<Boolean>[] target, ValidationMessageBundle bundle )
    {
        this( DEFAULT_PREFIX, DEFAULT_KEY, target, bundle );
    }

    public HasTrueValidator( String prefix, HasValue<Boolean>[] target, ValidationMessageBundle bundle )
    {
        this( prefix, DEFAULT_KEY, target, bundle );
    }
    
    public HasTrueValidator( String prefix, String key, HasValue<Boolean>[] target, ValidationMessageBundle bundle )
    {
        super( prefix, key, target, bundle );
    }

    @Override
    protected Object getValue()
    {
        Object value = null;

        if ( target != null )
        {
            for ( int i = 0; i < target.length; i++ )
            {
                Boolean v = target[i].getValue();
                
                if( v == true )
                {
                    value = v;
                    break;
                }
            }
        }
        
        return value;
    }

}
