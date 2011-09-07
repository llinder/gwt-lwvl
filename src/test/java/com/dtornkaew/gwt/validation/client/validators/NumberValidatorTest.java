package com.dtornkaew.gwt.validation.client.validators;

import java.util.Map;
import java.util.MissingResourceException;

import junit.framework.Assert;

import org.junit.Test;

import com.dtornkaew.gwt.validation.client.ValidationResult;
import com.dtornkaew.gwt.validation.client.Validator.HasValue;
import com.dtornkaew.gwt.validation.client.Validator.ValidationMessageBundle;

public class NumberValidatorTest
{
    @Test
    public void testRequired()
    {
        Dummy<Integer> d = new Dummy<Integer>();

        NumberValidator<Integer> v = new NumberValidator<Integer>( d, new Msg() );

        ValidationResult r = v.validate();

        Assert.assertNotNull( r );

        Assert.assertEquals( 1, r.getErrors().size() );
    }

    @Test
    public void testValid()
    {
        Dummy<Double> d = new Dummy<Double>( 5d );

        NumberValidator<Double> v = new NumberValidator<Double>( d, new Msg() );
        v.setMinValue( 5d );
        v.setMaxValue( 10d );

        ValidationResult r = v.validate();

        Assert.assertNotNull( r );

        Assert.assertEquals( 0, r.getErrors().size() );
    }

    @Test
    public void testMin()
    {
        Dummy<Double> d = new Dummy<Double>( 5d );

        NumberValidator<Double> v = new NumberValidator<Double>( d, new Msg() );
        v.setMinValue( 6d );
        v.setMaxValue( 10d );

        ValidationResult r = v.validate();

        Assert.assertNotNull( r );

        Assert.assertEquals( 1, r.getErrors().size() );

        Assert.assertEquals( "to low", r.getErrors().get( 0 ).getMessage() );
    }

    @Test
    public void testMax()
    {
        Dummy<Long> d = new Dummy<Long>( 5l );

        NumberValidator<Long> v = new NumberValidator<Long>( d, new Msg() );
        v.setMinValue( 2l );
        v.setMaxValue( 4l );

        ValidationResult r = v.validate();

        Assert.assertNotNull( r );

        Assert.assertEquals( 1, r.getErrors().size() );

        Assert.assertEquals( "to high", r.getErrors().get( 0 ).getMessage() );
    }

    class Msg
        implements ValidationMessageBundle
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
            if( "validation_required_required".equals( methodName ) )
            {
                return "required";
            }
            else if( "validation_number_lowerThanMin".equals( methodName ) )
            {
                return "to low";
            }
            else if( "validation_number_exceedsMax".equals( methodName ) )
            {
                return "to high";
            }
            return "";
        }

        @Override
        public String[] getStringArray( String methodName )
            throws MissingResourceException
        {
            // TODO Auto-generated method stub
            return null;
        }

    }

    class Dummy<T>
        implements HasValue<T>
    {
        private T value;
        
        public Dummy()
        {
            
        }

        public Dummy( T value )
        {
            this.value = value;
        }
        
        @Override
        public T getValue()
        {
            return value;
        }

    }

}
