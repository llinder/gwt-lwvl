package com.dtornkaew.gwt.validation.client.validators;


public class HasGwtTrueValidator
    extends RequiredValidator<com.google.gwt.user.client.ui.HasValue<Boolean>[]>
{
    public HasGwtTrueValidator( com.google.gwt.user.client.ui.HasValue<Boolean>[] target, ValidationMessageBundle bundle )
    {
        this( DEFAULT_PREFIX, DEFAULT_KEY, target, bundle );
    }
    
    public HasGwtTrueValidator( String prefix, com.google.gwt.user.client.ui.HasValue<Boolean>[] target, ValidationMessageBundle bundle )
    {
        this( prefix, DEFAULT_KEY, target, bundle );
    }

    public HasGwtTrueValidator( String prefix, String key, com.google.gwt.user.client.ui.HasValue<Boolean>[] target, ValidationMessageBundle bundle )
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
