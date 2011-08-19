package com.dtornkaew.gwt.validation.client.validators;

import junit.framework.Assert;

import org.junit.Test;

import com.dtornkaew.gwt.validation.client.ValidationResult;
import com.dtornkaew.gwt.validation.client.validators.NumberValidator.NumberMessageBundle;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasValue;

public class NumberValidatorTest
{
    @Test
    public void testValid()
    {
        Dummy d = new Dummy();
        d.setValue( "5" );
        
        NumberValidator v = new NumberValidator( new Msg(), d );
        v.setMinValue( 5d );
        v.setMaxValue( 10d );
        
        ValidationResult r = v.validate();
        
        Assert.assertNotNull( r );
        
        Assert.assertEquals( 0, r.getErrors().size() );
    }
    
    @Test
    public void testMin()
    {
        Dummy d = new Dummy();
        d.setValue( "5" );
        
        NumberValidator v = new NumberValidator( new Msg(), d );
        v.setMinValue( 6d );
        v.setMaxValue( 10d );
        
        ValidationResult r = v.validate();
        
        Assert.assertNotNull( r );
        
        Assert.assertEquals( 1, r.getErrors().size() );
    }
    
    @Test
    public void testMax()
    {
        Dummy d = new Dummy();
        d.setValue( "5" );
        
        NumberValidator v = new NumberValidator( new Msg(), d );
        v.setMinValue( 2d );
        v.setMaxValue( 4d );
        
        ValidationResult r = v.validate();
        
        Assert.assertNotNull( r );
        
        Assert.assertEquals( 1, r.getErrors().size() );
    }
}

class Msg implements NumberMessageBundle
{

    public String number_lowerThanMin( String min, String max )
    {
        // TODO Auto-generated method stub
        return null;
    }

    public String number_exceedsMax( String min, String max )
    {
        // TODO Auto-generated method stub
        return null;
    }
    
}

class Dummy implements HasValue<String>
{
    private String value;
    
    public HandlerRegistration addValueChangeHandler( ValueChangeHandler<String> handler )
    {
        // TODO Auto-generated method stub
        return null;
    }

    public void fireEvent( GwtEvent<?> event )
    {
        // TODO Auto-generated method stub
        
    }

    public String getValue()
    {
        return value;
    }

    public void setValue( String value )
    {
        this.value = value;
    }

    public void setValue( String value, boolean fireEvents )
    {
        this.value = value;
    }
    
}
