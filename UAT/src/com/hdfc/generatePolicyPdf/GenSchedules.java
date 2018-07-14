
package com.hdfc.generatePolicyPdf;

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
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "GenSchedules", targetNamespace = "http://tempuri.org/", wsdlLocation = "http://202.191.196.210/UAT/OnlineProducts/HdfcGenPDF/GenSchedules.asmx?WSDL")
public class GenSchedules
    extends Service
{

    private final static URL GENSCHEDULES_WSDL_LOCATION;
    private final static WebServiceException GENSCHEDULES_EXCEPTION;
    private final static QName GENSCHEDULES_QNAME = new QName("http://tempuri.org/", "GenSchedules");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://202.191.196.210/UAT/OnlineProducts/HdfcGenPDF/GenSchedules.asmx?WSDL");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        GENSCHEDULES_WSDL_LOCATION = url;
        GENSCHEDULES_EXCEPTION = e;
    }

    public GenSchedules() {
        super(__getWsdlLocation(), GENSCHEDULES_QNAME);
    }

    public GenSchedules(WebServiceFeature... features) {
        super(__getWsdlLocation(), GENSCHEDULES_QNAME, features);
    }

    public GenSchedules(URL wsdlLocation) {
        super(wsdlLocation, GENSCHEDULES_QNAME);
    }

    public GenSchedules(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, GENSCHEDULES_QNAME, features);
    }

    public GenSchedules(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public GenSchedules(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns GenSchedulesSoap
     */
    @WebEndpoint(name = "GenSchedulesSoap")
    public GenSchedulesSoap getGenSchedulesSoap() {
        return super.getPort(new QName("http://tempuri.org/", "GenSchedulesSoap"), GenSchedulesSoap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns GenSchedulesSoap
     */
    @WebEndpoint(name = "GenSchedulesSoap")
    public GenSchedulesSoap getGenSchedulesSoap(WebServiceFeature... features) {
        return super.getPort(new QName("http://tempuri.org/", "GenSchedulesSoap"), GenSchedulesSoap.class, features);
    }

    /**
     * 
     * @return
     *     returns GenSchedulesSoap
     */
    @WebEndpoint(name = "GenSchedulesSoap12")
    public GenSchedulesSoap getGenSchedulesSoap12() {
        return super.getPort(new QName("http://tempuri.org/", "GenSchedulesSoap12"), GenSchedulesSoap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns GenSchedulesSoap
     */
    @WebEndpoint(name = "GenSchedulesSoap12")
    public GenSchedulesSoap getGenSchedulesSoap12(WebServiceFeature... features) {
        return super.getPort(new QName("http://tempuri.org/", "GenSchedulesSoap12"), GenSchedulesSoap.class, features);
    }

    /**
     * 
     * @return
     *     returns GenSchedulesHttpGet
     */
    @WebEndpoint(name = "GenSchedulesHttpGet")
    public GenSchedulesHttpGet getGenSchedulesHttpGet() {
        return super.getPort(new QName("http://tempuri.org/", "GenSchedulesHttpGet"), GenSchedulesHttpGet.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns GenSchedulesHttpGet
     */
    @WebEndpoint(name = "GenSchedulesHttpGet")
    public GenSchedulesHttpGet getGenSchedulesHttpGet(WebServiceFeature... features) {
        return super.getPort(new QName("http://tempuri.org/", "GenSchedulesHttpGet"), GenSchedulesHttpGet.class, features);
    }

    /**
     * 
     * @return
     *     returns GenSchedulesHttpPost
     */
    @WebEndpoint(name = "GenSchedulesHttpPost")
    public GenSchedulesHttpPost getGenSchedulesHttpPost() {
        return super.getPort(new QName("http://tempuri.org/", "GenSchedulesHttpPost"), GenSchedulesHttpPost.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns GenSchedulesHttpPost
     */
    @WebEndpoint(name = "GenSchedulesHttpPost")
    public GenSchedulesHttpPost getGenSchedulesHttpPost(WebServiceFeature... features) {
        return super.getPort(new QName("http://tempuri.org/", "GenSchedulesHttpPost"), GenSchedulesHttpPost.class, features);
    }

    private static URL __getWsdlLocation() {
        if (GENSCHEDULES_EXCEPTION!= null) {
            throw GENSCHEDULES_EXCEPTION;
        }
        return GENSCHEDULES_WSDL_LOCATION;
    }

}
