package com.dtornkaew.gwt.validation.client;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SimpleValidationProcessorImpl
    implements ValidationProcessor
{
    private final List<ValidationResult> invalidResults = new LinkedList<ValidationResult>();

    private final Map<String, List<Validator<?>>> validators = new LinkedHashMap<String, List<Validator<?>>>();

    private final List<ValidationAction> globalActions = new LinkedList<ValidationAction>();

    public ValidationProcessor addValidators( String name, Validator<?>... validator )
    {
        if( validator == null || validator.length == 0 )
            return this;
        
        if ( !validators.containsKey( name ) )
            validators.put( name, new LinkedList<Validator<?>>() );

        final List<Validator<?>> namedList = validators.get( name );

        for ( Validator<?> v : validator )
            namedList.add( v );

        return this;
    }

    public ValidationProcessor addGlobalAction( ValidationAction action )
    {
        globalActions.add( action );

        return this;
    }

    public boolean validate( String... names )
    {
        invalidResults.clear();
        
        String[] keys = null;
        
        if( names != null && names.length > 0 )
        {
            keys = names;
        }
        else
        {
            keys = validators.keySet().toArray( new String[0] );
        }
        
        for( String key : keys )
        {
            List<Validator<?>> vlist = validators.get( key );
            for( Validator<?> v : vlist )
            {
                if( v.isEnabled() )
                {
                    ValidationResult r = v.validate();
                    if( r.getErrors().size() > 0 )
                    {
                        invalidResults.add( r );
                        v.performActions( r );
                    }
                }
            }
        }
        
        // Perform global actions.
        if( invalidResults.size() > 0 )
        {
            ValidationResult[] results = invalidResults.toArray( new ValidationResult[0] );
            for( ValidationAction ga : globalActions )
                ga.invoke( results );
        }
                            
        return ( invalidResults.size() == 0 );
    }
    
    protected List<ValidationResult> validateList( List<Validator<?>> validators )
    {
        final List<ValidationResult> results = new LinkedList<ValidationResult>();
        for( Validator<?> v : validators )
            results.add( v.validate() );
                        
        return results;
    }

    public void reset( String... names )
    {
        for ( String name : this.validators.keySet() )
        {
            List<Validator<?>> vals = this.validators.get( name );
            for ( Validator<?> val : vals )
            {
                val.resetActions();
            }
        }

        for ( ValidationAction action : globalActions )
            action.reset();
    }

    public List<ValidationResult> getInvalidResults()
    {
        return invalidResults;
    }

    public void removeAllValidators()
    {
        validators.clear();
    }

    public void removeAllGlobalActions()
    {
        globalActions.clear();
    }

    public void removeValidatorsAndGlobalActions()
    {
        removeAllValidators();
        removeAllGlobalActions();
    }

    public void removeValidators( String... names )
    {
        for ( String name : names )
            removeSingleValidator( name );
    }

    protected void removeSingleValidator( String name )
    {
        this.validators.remove( name );
    }

    public void removeGlobalActions( ValidationAction... actions )
    {
        for ( ValidationAction action : actions )
            globalActions.remove( action );
    }

    public List<Validator<?>> getValidators( String name )
    {
        return validators.get( name );
    }

    public Map<String, List<Validator<?>>> getAllValidators()
    {
        return validators;
    }

    public void setValidators( Map<String, List<Validator<?>>> validators )
    {
        removeAllValidators();

        for ( String name : validators.keySet() )
        {
            final List<Validator<?>> vals = validators.get( name );
            this.addValidators( name, vals.toArray( new Validator<?>[0] ) );
        }
    }

}
