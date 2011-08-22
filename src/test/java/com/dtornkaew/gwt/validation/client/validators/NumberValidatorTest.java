package com.dtornkaew.gwt.validation.client.validators;

import junit.framework.Assert;

import org.junit.Test;

import com.dtornkaew.gwt.validation.client.ValidationResult;
import com.dtornkaew.gwt.validation.client.validators.NumberValidator.NumberMessageBundle;
import com.dtornkaew.gwt.validation.client.validators.RequiredValidator.RequiredMessageBundle;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasValue;

public class NumberValidatorTest
{
    @Test
    public void testRequired()
    {
        Dummy d = new Dummy();

        NumberValidator<Integer> v = new NumberValidator<Integer>( d, new Msg() );

        ValidationResult r = v.validate();

        Assert.assertNotNull( r );

        Assert.assertEquals( 1, r.getErrors().size() );
    }

    @Test
    public void testValid()
    {
        Dummy d = new Dummy();
        d.setValue( "5" );

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
        Dummy d = new Dummy();
        d.setValue( "5" );

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
        Dummy d = new Dummy();
        d.setValue( "5" );

        NumberValidator<Long> v = new NumberValidator<Long>( d, new Msg() );
        v.setMinValue( 2l );
        v.setMaxValue( 4l );

        ValidationResult r = v.validate();

        Assert.assertNotNull( r );

        Assert.assertEquals( 1, r.getErrors().size() );

        Assert.assertEquals( "to high", r.getErrors().get( 0 ).getMessage() );
    }

    class Msg
        implements NumberMessageBundle, RequiredMessageBundle
    {

        public String validations_required_required()
        {
            return "required";
        }

        public String validation_number_lowerThanMin( String min, String max )
        {
            return "to low";
        }

        public String validation_number_exceedsMax( String min, String max )
        {
            return "to high";
        }
    }

    class Dummy
        implements HasValue<String>
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

}
