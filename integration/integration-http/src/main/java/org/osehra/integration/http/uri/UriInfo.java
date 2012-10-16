//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.1-b02-fcs
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2011.10.22 at 03:02:55 PM PDT
//

package org.osehra.integration.http.uri;

import org.osehra.integration.util.NullChecker;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.UriBuilder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * <p>
 * Java class for anonymous complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="pathParametersExt" type="{http://org/osehra/integration/http}multiValuedMap"/>
 *         &lt;element name="queryParametersExt" type="{http://org/osehra/integration/http}multiValuedMap"/>
 *         &lt;element name="baseUriExt" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="requestUriExt" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="absolutePathExt" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="pathExt" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "pathParametersExt", "queryParametersExt",
		"baseUriExt", "requestUriExt", "absolutePathExt", "pathExt" })
@XmlRootElement(name = "uriInfo")
public class UriInfo implements javax.ws.rs.core.UriInfo {

	@XmlElement(required = true)
	protected String absolutePathExt;
	@XmlElement(required = true)
	protected String baseUriExt;
	@XmlElement(required = true)
	protected String pathExt;
	@XmlElement(required = true)
	protected MultiValuedMap pathParametersExt;
	@XmlElement(required = true)
	protected MultiValuedMap queryParametersExt;
	@XmlElement(required = true)
	protected String requestUriExt;

	@Override
	public URI getAbsolutePath() {
		if (NullChecker.isNotEmpty(this.absolutePathExt)) {
			try {
				return new URI(this.absolutePathExt);
			} catch (final URISyntaxException ex) {
				throw new RuntimeException(ex);
			}
		}
		return null;
	}

	@Override
	public UriBuilder getAbsolutePathBuilder() {
		throw new RuntimeException("Not implemented!");
	}

	/**
	 * Gets the value of the absolutePathExt property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getAbsolutePathExt() {
		return this.absolutePathExt;
	}

	@Override
	public URI getBaseUri() {
		if (NullChecker.isNotEmpty(this.baseUriExt)) {
			try {
				return new URI(this.baseUriExt);
			} catch (final URISyntaxException ex) {
				throw new RuntimeException(ex);
			}
		}
		return null;
	}

	@Override
	public UriBuilder getBaseUriBuilder() {
		throw new RuntimeException("Not implemented!");
	}

	/**
	 * Gets the value of the baseUriExt property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getBaseUriExt() {
		return this.baseUriExt;
	}

	@Override
	public List<Object> getMatchedResources() {
		throw new RuntimeException("Not implemented!");
	}

	@Override
	public List<String> getMatchedURIs() {
		throw new RuntimeException("Not implemented!");
	}

	@Override
	public List<String> getMatchedURIs(final boolean decode) {
		throw new RuntimeException("Not implemented!");
	}

	@Override
	public String getPath() {
		return this.pathExt;
	}

	@Override
	public String getPath(final boolean decode) {
		return this.pathExt;
	}

	/**
	 * Gets the value of the pathExt property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPathExt() {
		return this.pathExt;
	}

	@Override
	public MultivaluedMap<String, String> getPathParameters() {
		return this.getPathParameters(false);
	}

	@Override
	public MultivaluedMap<String, String> getPathParameters(final boolean decode) {
		final MultivaluedMap<String, String> map = new MultivaluedMapImpl();
		if (NullChecker.isNotEmpty(this.pathParametersExt)) {
			for (final MultiValuedMap.Entry entry : this.pathParametersExt
					.getEntry()) {
				final String key = entry.getKey();
				for (final String value : entry.getValue()) {
					map.add(key, value);
				}
			}
		}
		return map;
	}

	/**
	 * Gets the value of the pathParametersExt property.
	 * 
	 * @return possible object is {@link MultiValuedMap }
	 * 
	 */
	public MultiValuedMap getPathParametersExt() {
		return this.pathParametersExt;
	}

	@Override
	public List<PathSegment> getPathSegments() {
		throw new RuntimeException("Not implemented!");
	}

	@Override
	public List<PathSegment> getPathSegments(final boolean decode) {
		throw new RuntimeException("Not implemented!");
	}

	@Override
	public MultivaluedMap<String, String> getQueryParameters() {
		return this.getQueryParameters(false);
	}

	@Override
	public MultivaluedMap<String, String> getQueryParameters(
			final boolean decode) {
		final MultivaluedMap<String, String> map = new MultivaluedMapImpl();
		if (NullChecker.isNotEmpty(this.queryParametersExt)) {
			for (final MultiValuedMap.Entry entry : this.queryParametersExt
					.getEntry()) {
				final String key = entry.getKey();
				for (final String value : entry.getValue()) {
					map.add(key, value);
				}
			}
		}
		return map;
	}

	/**
	 * Gets the value of the queryParametersExt property.
	 * 
	 * @return possible object is {@link MultiValuedMap }
	 * 
	 */
	public MultiValuedMap getQueryParametersExt() {
		return this.queryParametersExt;
	}

	@Override
	public URI getRequestUri() {
		if (NullChecker.isNotEmpty(this.requestUriExt)) {
			try {
				return new URI(this.requestUriExt);
			} catch (final URISyntaxException ex) {
				throw new RuntimeException(ex);
			}
		}
		return null;
	}

	@Override
	public UriBuilder getRequestUriBuilder() {
		throw new RuntimeException("Not implemented!");
	}

	/**
	 * Gets the value of the requestUriExt property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getRequestUriExt() {
		return this.requestUriExt;
	}

	public void setAbsolutePath(final URI uri) {
		if (NullChecker.isNotEmpty(uri)) {
			this.absolutePathExt = uri.toString();
		}
	}

	/**
	 * Sets the value of the absolutePathExt property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setAbsolutePathExt(final String value) {
		this.absolutePathExt = value;
	}

	public void setBaseUri(final URI uri) {
		if (NullChecker.isNotEmpty(uri)) {
			this.baseUriExt = uri.toString();
		}
	}

	/**
	 * Sets the value of the baseUriExt property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setBaseUriExt(final String value) {
		this.baseUriExt = value;
	}

	public void setPath(final String path) {
		this.pathExt = path;
	}

	/**
	 * Sets the value of the pathExt property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPathExt(final String value) {
		this.pathExt = value;
	}

	public void setPathParameters(
			final MultivaluedMap<String, String> multiValuedContextPathParametersMap) {
		// Set path parameters
		this.pathParametersExt = new org.osehra.integration.http.uri.MultiValuedMap();
		if (NullChecker.isNotEmpty(multiValuedContextPathParametersMap)) {
			for (final Map.Entry<String, List<String>> entry : multiValuedContextPathParametersMap
					.entrySet()) {
				final org.osehra.integration.http.uri.MultiValuedMap.Entry pathEntry = new org.osehra.integration.http.uri.MultiValuedMap.Entry();
				pathEntry.setKey(entry.getKey());
				final List<String> pathValues = entry.getValue();
				for (final String pathValue : pathValues) {
					pathEntry.getValue().add(pathValue);
				}
				this.pathParametersExt.getEntry().add(pathEntry);
			}
		}
	}

	/**
	 * Sets the value of the pathParametersExt property.
	 * 
	 * @param value
	 *            allowed object is {@link MultiValuedMap }
	 * 
	 */
	public void setPathParametersExt(final MultiValuedMap value) {
		this.pathParametersExt = value;
	}

	public void setQueryParameters(
			final MultivaluedMap<String, String> multiValuedContextPathParametersMap) {
		// Set path parameters
		this.queryParametersExt = new org.osehra.integration.http.uri.MultiValuedMap();
		if (NullChecker.isNotEmpty(multiValuedContextPathParametersMap)) {
			for (final Map.Entry<String, List<String>> entry : multiValuedContextPathParametersMap
					.entrySet()) {
				final org.osehra.integration.http.uri.MultiValuedMap.Entry pathEntry = new org.osehra.integration.http.uri.MultiValuedMap.Entry();
				pathEntry.setKey(entry.getKey());
				final List<String> pathValues = entry.getValue();
				for (final String pathValue : pathValues) {
					pathEntry.getValue().add(pathValue);
				}
				this.queryParametersExt.getEntry().add(pathEntry);
			}
		}
	}

	/**
	 * Sets the value of the queryParametersExt property.
	 * 
	 * @param value
	 *            allowed object is {@link MultiValuedMap }
	 * 
	 */
	public void setQueryParametersExt(final MultiValuedMap value) {
		this.queryParametersExt = value;
	}

	public void setRequestUri(final URI uri) {
		if (NullChecker.isNotEmpty(uri)) {
			this.requestUriExt = uri.toString();
		}
	}

	/**
	 * Sets the value of the requestUriExt property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setRequestUriExt(final String value) {
		this.requestUriExt = value;
	}	

	@Override
	public String toString() {
		return this.getRequestUri().toString();				
	}
}
