package com.dtornkaew.gwt.validation.client;

import java.util.LinkedList;
import java.util.List;

import com.dtornkaew.gwt.validation.client.i18n.MessageProvider;

public class ValidationResult
{    
    public class ValidationError<C>
    {
        private final MessageProvider<C> messageProvider;
        
        private final C code;
        
        public ValidationError( C code, MessageProvider<C> messageProvider )
        {
            this.code = code;
            this.messageProvider = messageProvider;
        }
        public C getCode()
        {
            return code;
        }
        public String getMessage()
        {
            return messageProvider.getMessage( code );
        }
    }
    
    private final List<ValidationError<?>> errors;
    
    private final Validator validator;
    
    public ValidationResult( Validator validator )
    {
        this.validator = validator;
        this.errors = new LinkedList<ValidationError<?>>();
    }
    
    public void addError( ValidationError<?> error )
    {
        this.errors.add( error );
    }
    
    public Validator getValidator()
    {
        return validator;
    }
    
    public List<ValidationError<?>> getErrors()
    {
        return errors;
    }
    
    public List<String> getMessages()
    {
        List<String> msgs = new LinkedList<String>();
        for( ValidationError<?> error : errors )
        {
            msgs.add( error.getMessage() );
        }
        return msgs;
    }
}
