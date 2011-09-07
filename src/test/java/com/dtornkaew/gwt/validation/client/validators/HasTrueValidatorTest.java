package com.dtornkaew.gwt.validation.client.validators;

import java.util.Map;
import java.util.MissingResourceException;

import junit.framework.Assert;

import org.junit.Test;

import com.dtornkaew.gwt.validation.client.ValidationResult;
import com.dtornkaew.gwt.validation.client.Validator.HasValue;
import com.dtornkaew.gwt.validation.client.Validator.ValidationMessageBundle;

public class HasTrueValidatorTest
{
    @Test
    public void testRequiredOk()
    {
        final ValidationMessageBundle bundle = new Msg();
        
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
        final ValidationMessageBundle bundle = new Msg();
        
        Dummy[] values = new Dummy[]{
            new Dummy( false ),
            new Dummy( false )
        };
        
        HasTrueValidator v = new HasTrueValidator( values, bundle );
        
        ValidationResult r = v.validate();
        
        Assert.assertEquals( 1, r.getErrors().size() );
    }
    
    
    class Msg implements ValidationMessageBundle
    {

        @Override
        public boolean getBoolean( String methodName )
            throws MissingResourceException
        {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public double getDouble( String methodName )
            throws MissingResourceException
        {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public float getFloat( String methodName )
            throws MissingResourceException
        {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public int getInt( String methodName )
            throws MissingResourceException
        {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public Map<String, String> getMap( String methodName )
            throws MissingResourceException
        {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public String getString( String methodName )
            throws MissingResourceException
        {
            return "required";
        }

        @Override
        public String[] getStringArray( String methodName )
            throws MissingResourceException
        {
            // TODO Auto-generated method stub
            return null;
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
        public Boolean getValue()
        {
            return value;
        }
    }
}


