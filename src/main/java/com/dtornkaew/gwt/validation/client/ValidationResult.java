package com.dtornkaew.gwt.validation.client;

import java.util.LinkedList;
import java.util.List;

import com.dtornkaew.gwt.validation.client.i18n.MessageProvider;

public class ValidationResult
{
    public class ValidationError
    {
        private final MessageProvider messageProvider;
        
        private final int code;
        
        public ValidationError( int code, MessageProvider messageProvider )
        {
            this.code = code;
            this.messageProvider = messageProvider;
        }
        public int getCode()
        {
            return code;
        }
        public String getMessage()
        {
            return messageProvider.getMessage( code );
        }
    }
    
    private List<ValidationError> errors;
    
    private final Validator validator;
    
    public ValidationResult( Validator validator )
    {
        this.validator = validator;
    }
    
    public Validator getValidator()
    {
        return validator;
    }
    
    public List<ValidationError> getErrors()
    {
        return errors;
    }
    
    public List<String> getMessages()
    {
        List<String> msgs = new LinkedList<String>();
        for( ValidationError error : errors )
        {
            msgs.add( error.getMessage() );
        }
        return msgs;
    }
}
