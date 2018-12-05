package com.ledify.batch.notification.batchProcessor.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jca.cci.InvalidResultSetAccessException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.IncorrectResultSetColumnCountException;
import org.springframework.jdbc.JdbcUpdateAffectedIncorrectNumberOfRowsException;
import org.springframework.jdbc.SQLWarningException;
import org.springframework.jdbc.UncategorizedSQLException;

public class BatchException extends RuntimeException {
	private boolean isfatal;
	private static final Map<String, Object> PROPERTIES = new HashMap<String, Object>();

	public BatchException(final String message, final Throwable cause) {
		super(message, cause);
		this.isfatal = isFatal(cause);
	}
	
	 private boolean isFatal(final Throwable cause) {
	        boolean fatalError = false;

	        if (cause instanceof DataAccessException) {
	            DataAccessException dae = (DataAccessException) cause;

	            fatalError = checkException(dae);
	        }

	        return fatalError;
	    }
	 
	 
	 /**
	     * check instance of spring Jdbc exception
	     * 
	     * @param dae
	     * @return
	     */
	    private boolean checkException(final DataAccessException dae) {
	        boolean fatalError = false;

	        
	        if (dae instanceof BadSqlGrammarException
	                || dae instanceof CannotGetJdbcConnectionException
	                || dae instanceof IncorrectResultSetColumnCountException
	                ||dae instanceof DuplicateKeyException
	                ||dae instanceof DataIntegrityViolationException) {
	            fatalError = true;
	        } else if (dae instanceof InvalidResultSetAccessException
	                || dae instanceof JdbcUpdateAffectedIncorrectNumberOfRowsException
	                || dae instanceof SQLWarningException
	                // added UncategorizedSQLException, on 04-04-2016 by koushik. 
	                //Added to check on condition, if data types are mismatched when you are retrieving the field values. Esp from VIEW to mapper/DTO. 
	                //Keep an eye if its not breaking anything else.
	                || dae instanceof UncategorizedSQLException
	                //Adding DuplicateKeyException for condition if the unique Key constraint is ever caught 
	                ||dae instanceof DuplicateKeyException 
	                //Adding DataIntegrityViolationException for condition if the unique Key constraint is ever caught   
	                || dae instanceof DataIntegrityViolationException)  { 
	            fatalError = true;
	        }
	        return fatalError;
	    }
	    
	    public static BatchException wrap(final Throwable exception) {
	        if (exception instanceof BatchException) {
	        	BatchException se = (BatchException) exception;
	            
	                return new BatchException(exception.getMessage(),
	                        exception);
	            
	        } else {
	            return new BatchException(exception.getMessage(), exception
	                   );
	        }
	    }



}
