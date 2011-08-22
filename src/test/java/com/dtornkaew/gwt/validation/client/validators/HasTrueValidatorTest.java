package com.dtornkaew.gwt.validation.client.validators;

import junit.framework.Assert;

import org.junit.Test;

import com.dtornkaew.gwt.validation.client.ValidationResult;
import com.dtornkaew.gwt.validation.client.validators.RequiredValidator.RequiredMessageBundle;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasValue;

public class HasTrueValidatorTest
{
    @Test
    public void testRequiredOk()
    {
        final RequiredMessageBundle bundle = new Msg();
        
        Dummy[] values = new Dummy[]{
            new Dummy( true ),
            new Dummy( false )
        };
        
        HasTrueValidator v = new HasTrueValidator( values, bundle );
        
        ValidationResult r = v.validate();
        
        Assert.assertEquals( 0, r.getErrors().size() );
    }
    
    @Test
    public void testRequiredFail()
    {
        final RequiredMessageBundle bundle = new Msg();
        
        Dummy[] values = new Dummy[]{
            new Dummy( false ),
            new Dummy( false )
        };
        
        HasTrueValidator v = new HasTrueValidator( values, bundle );
        
        ValidationResult r = v.validate();
        
        Assert.assertEquals( 1, r.getErrors().size() );
    }
    
    
    class Msg implements RequiredMessageBundle
    {
        public String validations_required_required()
        {
            return "required";
        } 
    }

    class Dummy implements HasValue<Boolean>
    {
        private Boolean value;
        
        public Dummy( boolean b )
        {
            value = b;
        }

        @Override
        public HandlerRegistration addValueChangeHandler( ValueChangeHandler<Boolean> handler )
        {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public void fireEvent( GwtEvent<?> event )
        {
            // TODO Auto-generated method stub
            
        }

        @Override
        public Boolean getValue()
        {
            return value;
        }

        @Override
        public void setValue( Boolean value )
        {
            this.value = value;
        }

        @Override
        public void setValue( Boolean value, boolean fireEvents )
        {
            this.value = value;
        }
        
    }
}


