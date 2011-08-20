package com.dtornkaew.gwt.validation.client;

import java.util.List;
import java.util.Map;

public interface ValidationProcessor
{
    /**
     * Add new validators that the <code>ValidationProcessor</code> instance will validate.
     * 
     * @param logical grouping name for the validators.
     * @param validator The validators that will be used for validations
     */
    public ValidationProcessor addValidators( String name, Validator<?>... validator );

    /**
     * Adds an global action to this <code>ValidatorProcessor</code> instance. This is primarily useful for actions that
     * require all the validation errors of a validation run. The actions added as global actions will only be invoked
     * <i>after</i> all validations and their associated actions are invoked and only if there were validation errors
     * during the validation run.
     * 
     * @param action The global action
     * @return A reference to this <code>ValidationProcessor</code>'s instance for chaining.
     */
    public ValidationProcessor addGlobalAction( ValidationAction action );

    /**
     * Invoke the validation.
     * 
     * @param names The names of the validations that should be validated, can be <code>null</code> or not given to
     *            validate everything.
     * @return <code>true</code> if all validations were successful, <code>false</code> otherwise
     */
    public boolean validate( String... names );
    
    /**
     * Resets all the actions specified by a possibly failed previous validation run.
     * If the names parameter is given only the specified validator actions are reset.
     * 
     * @param names The names of the validations that should be reset, can be <code>null</code> to reset everything.
     */
    public void reset(String... names);
    
    public List<ValidationResult> getInvalidResults();

    /**
     * Removes all validators.
     */
    public void removeAllValidators();

    /**
     * Removes all global actions.
     */
    public void removeAllGlobalActions();

    /**
     * Resets the <code>ValidationProcessor</code>. Invokes <code>removeAllValidators</code> and
     * <code>removeAllGlobalActions</code>
     */
    public void removeValidatorsAndGlobalActions();

    /**
     * Removes all the validators associated with the given names
     * 
     * @param validatorNames The names of the added validators to delete
     */
    public void removeValidators( String... names );

    /**
     * Removes all the given actions from the global actions list
     * 
     * @param instancesToRemove The instances to remove
     */
    public void removeGlobalActions( ValidationAction... instancesToRemove );

    /**
     * Returns all the validators associated with the given name
     * 
     * @param name
     * @return The validators found
     */
    public List<Validator<?>> getValidators( String name );

    /**
     * Return all the validators currently added to the validation processor
     * 
     * @return All the validators
     */
    public Map<String, List<Validator<?>>> getAllValidators();

    /**
     * Cleans the currently set validators and sets to the specified validators
     * 
     * @param validators The validators to set for this ValidationProcessor instance
     */
    public void setValidators( Map<String, List<Validator<?>>> validators );

}
