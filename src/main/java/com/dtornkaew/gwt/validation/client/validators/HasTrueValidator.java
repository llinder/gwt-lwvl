package com.dtornkaew.gwt.validation.client.validators;

import com.google.gwt.i18n.client.Messages;
import com.google.gwt.user.client.ui.HasValue;

public class HasTrueValidator
    extends RequiredValidator<HasValue<Boolean>[]>
{

    public HasTrueValidator( HasValue<Boolean>[] target, Messages ... messages )
    {
        super( target, messages );
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
