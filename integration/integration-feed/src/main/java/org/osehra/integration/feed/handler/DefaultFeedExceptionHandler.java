package org.osehra.integration.feed.handler;

import org.osehra.integration.core.error.ExceptionHandler;
import org.osehra.integration.core.error.ExceptionReportUtil;
import org.osehra.integration.core.error.HandlerException;
import org.osehra.integration.core.interceptor.InterceptorException;
import org.osehra.integration.core.router.RouterException;
import org.osehra.integration.core.service.ServiceInvocationException;
import org.osehra.integration.core.transformer.Transformer;
import org.osehra.integration.core.transformer.TransformerException;
import org.osehra.integration.feed.creator.CoreFeedCreator;
import org.osehra.integration.http.HttpServiceInvocationException;
import org.osehra.integration.http.uri.UriInfo;
import org.osehra.integration.util.NullChecker;

import java.net.URI;
import java.util.List;

import org.apache.abdera.model.Feed;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Required;
import org.w3c.dom.Document;

/**
 * 
 * @author John W. May
 *
 */
public class DefaultFeedExceptionHandler implements ExceptionHandler<Object> {

	private static final Log LOG = LogFactory
			.getLog(DefaultFeedExceptionHandler.class);
	
	private List<Class<Exception>> reportExceptionClasses;
	
	private CoreFeedCreator coreFeedCreator;

	private DasExtensionUtil dasExtensionUtil;	
	
	private Transformer<Document, UriInfo> xmlToContextUriInfo;

	private Transformer<Feed, String> feedToString;
	
	private String componentName = "";
	
	private String componentId = "";
	
	/**
	 * Handles a caught Exception thrown if the ex Exception's underlying 
	 * cause is of a type of Exception listed in the reportExceptionClasses 
	 * property List, and is listed in the code below.  If a caught Exception 
	 * is of a type not handled here then it is re-thrown inside a 
	 * HandlerException.  The Object message parameter
	 * must contain the original request XML Document message parameter.
	 * 
	 * @param message - the original message instance at the time of the exception throw
	 * @param ex - the exception thrown and being handled here
	 * @param componentName - text name of the component throwing the Exception 
	 * @param componentId - alphanumeric code identify the component throwing the Exception
	 *  
	 * @return the default Feed containing the exception information for 
	 * the Consumer
	 * 
	 * @throws HandlerException 
	 */
	@Override
	public Object handleException(Object message, Exception ex, String componentName, String componentId)
			throws HandlerException {
		
		String feedStr = "";
		Feed defaultFeed = null;
		String feedComponentName = "";
		String feedComponentId = "";
		
		// If componentName or componentId rec'd  is empty, put the 
		// property componentName/componentId values here
		if(NullChecker.isNotEmpty(componentName)) {
			feedComponentName = componentName;
		} else {
			feedComponentName = this.componentName;
		}
		if(NullChecker.isNotEmpty(componentId)) {
			feedComponentId = componentId;
		} else {
			feedComponentId = this.componentId;
		}
		
		try {	
			// Get the desired Exception, even if wrapped in the given Exception ex, if it exists in reportExceptionClasses
			// NOTE: There can only be one Feed generated by this loop since ex only has one Exception type
			for(Class<Exception> currentReportExceptionClass: this.reportExceptionClasses) {
				
				Throwable th = ExceptionReportUtil.getReportException(ex, currentReportExceptionClass);			
				Exception rootCauseException = (Exception)th;				
				
				if ((NullChecker.isEmpty(defaultFeed)) && (rootCauseException instanceof HttpServiceInvocationException)) {					
					
					LOG.trace("DefaultFeedExceptionHandler is handling an HttpServiceInvocationException");
					
					HttpServiceInvocationException hsiEx = (HttpServiceInvocationException) rootCauseException;
					
					String hsiExceptionMessage = hsiEx.getMessage();
					int httpStatusCode = hsiEx.getErrorCode();
					URI producerUri = hsiEx.getRemoteResourceURI();
										
					org.osehra.integration.http.uri.UriInfo clientRequestUriInfo = this.xmlToContextUriInfo.transform((Document)message);				
										
					defaultFeed = this.coreFeedCreator.createFeed();			

					defaultFeed = this.dasExtensionUtil.getDefaultFeedForHttpException(defaultFeed, 
						clientRequestUriInfo, producerUri, httpStatusCode, 
						hsiExceptionMessage, feedComponentName, feedComponentId);					
						
				} else if ((NullChecker.isEmpty(defaultFeed)) && ( (rootCauseException instanceof ServiceInvocationException) || (rootCauseException instanceof TransformerException) || (rootCauseException instanceof RouterException) || (rootCauseException instanceof InterceptorException))) {
					
					LOG.trace("DefaultFeedExceptionHandler is handling an "+rootCauseException.getClass());
					
					defaultFeed = this.coreFeedCreator.createFeed();			

					defaultFeed = this.dasExtensionUtil.getDefaultFeedForInternalException(defaultFeed, "Internal Error", feedComponentName, feedComponentId);						
				} 
			}			
			if(NullChecker.isNotEmpty(defaultFeed)) {
				// Log the Exception stack trace here since the Exception goes no further
				LOG.error("DefaultFeedExceptionHandler: "+ex.getMessage(), ex);
				
				feedStr = this.feedToString.transform(defaultFeed);				
			} else {
				LOG.trace("the received Exception "+ex.getClass()+" was not handled in DefaultFeedExceptionHandler.");
				throw ex;
			}
			return feedStr;		
		} catch(final Exception e) {
			throw new HandlerException(e);
		}	
	}	
	
	@Override
	public Object handleException(Object t, Exception ex)
			throws HandlerException {		
		return this.handleException(t, ex, "", "");
	}

	@Required
	public void setReportExceptionClasses(
			List<Class<Exception>> reportExceptionClasses) {
		this.reportExceptionClasses = reportExceptionClasses;
	}

	@Required
	public void setCoreFeedCreator(CoreFeedCreator coreFeedCreator) {
		this.coreFeedCreator = coreFeedCreator;
	}

	@Required
	public void setDasExtensionUtil(DasExtensionUtil dasExtensionUtil) {
		this.dasExtensionUtil = dasExtensionUtil;
	}

	@Required
	public void setXmlToContextUriInfo(
			org.osehra.integration.http.transformer.XmlToContextUriInfo xmlToContextUriInfo) {
		this.xmlToContextUriInfo = xmlToContextUriInfo;
	}

	@Required
	public void setFeedToString(
			org.osehra.integration.feed.transformer.FeedToString feedToString) {
		this.feedToString = feedToString;
	}

	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

	public void setComponentId(String componentId) {
		this.componentId = componentId;
	}

}
