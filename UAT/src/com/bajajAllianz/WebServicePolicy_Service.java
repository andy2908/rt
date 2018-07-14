
package com.bajajAllianz;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "WebServicePolicy", targetNamespace = "http://com/bajajallianz/motWebPolicy/WebServicePolicy.wsdl", wsdlLocation = "http://webservicesdev.bajajallianz.com/WebServicePolicy/WebServicePolicyPort?WSDL")
public class WebServicePolicy_Service
    extends Service
{

    private final static URL WEBSERVICEPOLICY_WSDL_LOCATION;
    private final static WebServiceException WEBSERVICEPOLICY_EXCEPTION;
    private final static QName WEBSERVICEPOLICY_QNAME = new QName("http://com/bajajallianz/motWebPolicy/WebServicePolicy.wsdl", "WebServicePolicy");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://webservicesdev.bajajallianz.com/WebServicePolicy/WebServicePolicyPort?WSDL");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        WEBSERVICEPOLICY_WSDL_LOCATION = url;
        WEBSERVICEPOLICY_EXCEPTION = e;
    }

    public WebServicePolicy_Service() {
        super(__getWsdlLocation(), WEBSERVICEPOLICY_QNAME);
    }

    public WebServicePolicy_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), WEBSERVICEPOLICY_QNAME, features);
    }

    public WebServicePolicy_Service(URL wsdlLocation) {
        super(wsdlLocation, WEBSERVICEPOLICY_QNAME);
    }

    public WebServicePolicy_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, WEBSERVICEPOLICY_QNAME, features);
    }

    public WebServicePolicy_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public WebServicePolicy_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns WebServicePolicy
     */
    @WebEndpoint(name = "WebServicePolicyPort")
    public WebServicePolicy getWebServicePolicyPort() {
        return super.getPort(new QName("http://com/bajajallianz/motWebPolicy/WebServicePolicy.wsdl", "WebServicePolicyPort"), WebServicePolicy.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns WebServicePolicy
     */
    @WebEndpoint(name = "WebServicePolicyPort")
    public WebServicePolicy getWebServicePolicyPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://com/bajajallianz/motWebPolicy/WebServicePolicy.wsdl", "WebServicePolicyPort"), WebServicePolicy.class, features);
    }

    private static URL __getWsdlLocation() {
        if (WEBSERVICEPOLICY_EXCEPTION!= null) {
            throw WEBSERVICEPOLICY_EXCEPTION;
        }
        return WEBSERVICEPOLICY_WSDL_LOCATION;
    }

}