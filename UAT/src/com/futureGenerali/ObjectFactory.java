
package com.futureGenerali;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.futureGenerali package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _UnsignedLong_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "unsignedLong");
    private final static QName _UnsignedByte_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "unsignedByte");
    private final static QName _UnsignedInt_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "unsignedInt");
    private final static QName _Char_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "char");
    private final static QName _Short_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "short");
    private final static QName _Guid_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "guid");
    private final static QName _UnsignedShort_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "unsignedShort");
    private final static QName _DataSet_QNAME = new QName("", "DataSet");
    private final static QName _Decimal_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "decimal");
    private final static QName _Boolean_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "boolean");
    private final static QName _Duration_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "duration");
    private final static QName _Base64Binary_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "base64Binary");
    private final static QName _Int_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "int");
    private final static QName _Long_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "long");
    private final static QName _AnyURI_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "anyURI");
    private final static QName _Float_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "float");
    private final static QName _DateTime_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "dateTime");
    private final static QName _Byte_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "byte");
    private final static QName _Double_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "double");
    private final static QName _QName_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "QName");
    private final static QName _AnyType_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "anyType");
    private final static QName _String_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "string");
    private final static QName _FetchPinCodePincode_QNAME = new QName("http://tempuri.org/", "Pincode");
    private final static QName _CreateOnlyReceiptXML_QNAME = new QName("http://tempuri.org/", "XML");
    private final static QName _CreatePolicyResponseCreatePolicyResult_QNAME = new QName("http://tempuri.org/", "CreatePolicyResult");
    private final static QName _CreateOnlyReceiptResponseCreateOnlyReceiptResult_QNAME = new QName("http://tempuri.org/", "CreateOnlyReceiptResult");
    private final static QName _CreatePolicyWithPayerResponseCreatePolicyWithPayerResult_QNAME = new QName("http://tempuri.org/", "CreatePolicyWithPayerResult");
    private final static QName _CreateAndUpdateCorporateClientResponseCreateAndUpdateCorporateClientResult_QNAME = new QName("http://tempuri.org/", "CreateAndUpdateCorporateClientResult");
    private final static QName _IsClaimPolicyno_QNAME = new QName("http://tempuri.org/", "policyno");
    private final static QName _CreatePolicyProduct_QNAME = new QName("http://tempuri.org/", "Product");
    private final static QName _CreateAndUpdateClientResponseCreateAndUpdateClientResult_QNAME = new QName("http://tempuri.org/", "CreateAndUpdateClientResult");
    private final static QName _IsClaimResponseIsClaimResult_QNAME = new QName("http://tempuri.org/", "isClaimResult");
    private final static QName _FetchPinCodeResponseFetchPinCodeResult_QNAME = new QName("http://tempuri.org/", "FetchPinCodeResult");
    private final static QName _CreatePolicyWithPayerMultiReceipt_QNAME = new QName("http://tempuri.org/", "multiReceipt");
    private final static QName _CreatePolicyWithPayerPayerID_QNAME = new QName("http://tempuri.org/", "PayerID");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.futureGenerali
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link IsClaimResponse }
     * 
     */
    public IsClaimResponse createIsClaimResponse() {
        return new IsClaimResponse();
    }

    /**
     * Create an instance of {@link FetchPinCodeResponse }
     * 
     */
    public FetchPinCodeResponse createFetchPinCodeResponse() {
        return new FetchPinCodeResponse();
    }

    /**
     * Create an instance of {@link CreateAndUpdateClient }
     * 
     */
    public CreateAndUpdateClient createCreateAndUpdateClient() {
        return new CreateAndUpdateClient();
    }

    /**
     * Create an instance of {@link CreatePolicy }
     * 
     */
    public CreatePolicy createCreatePolicy() {
        return new CreatePolicy();
    }

    /**
     * Create an instance of {@link CreatePolicyWithPayer }
     * 
     */
    public CreatePolicyWithPayer createCreatePolicyWithPayer() {
        return new CreatePolicyWithPayer();
    }

    /**
     * Create an instance of {@link CreatePolicyWithPayerResponse }
     * 
     */
    public CreatePolicyWithPayerResponse createCreatePolicyWithPayerResponse() {
        return new CreatePolicyWithPayerResponse();
    }

    /**
     * Create an instance of {@link CreateAndUpdateCorporateClient }
     * 
     */
    public CreateAndUpdateCorporateClient createCreateAndUpdateCorporateClient() {
        return new CreateAndUpdateCorporateClient();
    }

    /**
     * Create an instance of {@link CreateAndUpdateClientResponse }
     * 
     */
    public CreateAndUpdateClientResponse createCreateAndUpdateClientResponse() {
        return new CreateAndUpdateClientResponse();
    }

    /**
     * Create an instance of {@link IsClaimResponse.IsClaimResult }
     * 
     */
    public IsClaimResponse.IsClaimResult createIsClaimResponseIsClaimResult() {
        return new IsClaimResponse.IsClaimResult();
    }

    /**
     * Create an instance of {@link FetchPinCodeResponse.FetchPinCodeResult }
     * 
     */
    public FetchPinCodeResponse.FetchPinCodeResult createFetchPinCodeResponseFetchPinCodeResult() {
        return new FetchPinCodeResponse.FetchPinCodeResult();
    }

    /**
     * Create an instance of {@link CreatePolicyResponse }
     * 
     */
    public CreatePolicyResponse createCreatePolicyResponse() {
        return new CreatePolicyResponse();
    }

    /**
     * Create an instance of {@link CreateOnlyReceipt }
     * 
     */
    public CreateOnlyReceipt createCreateOnlyReceipt() {
        return new CreateOnlyReceipt();
    }

    /**
     * Create an instance of {@link CreateOnlyReceiptResponse }
     * 
     */
    public CreateOnlyReceiptResponse createCreateOnlyReceiptResponse() {
        return new CreateOnlyReceiptResponse();
    }

    /**
     * Create an instance of {@link FetchPinCode }
     * 
     */
    public FetchPinCode createFetchPinCode() {
        return new FetchPinCode();
    }

    /**
     * Create an instance of {@link CreateAndUpdateCorporateClientResponse }
     * 
     */
    public CreateAndUpdateCorporateClientResponse createCreateAndUpdateCorporateClientResponse() {
        return new CreateAndUpdateCorporateClientResponse();
    }

    /**
     * Create an instance of {@link IsClaim }
     * 
     */
    public IsClaim createIsClaim() {
        return new IsClaim();
    }

    /**
     * Create an instance of {@link DataSet }
     * 
     */
    public DataSet createDataSet() {
        return new DataSet();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "unsignedLong")
    public JAXBElement<BigInteger> createUnsignedLong(BigInteger value) {
        return new JAXBElement<BigInteger>(_UnsignedLong_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "unsignedByte")
    public JAXBElement<Short> createUnsignedByte(Short value) {
        return new JAXBElement<Short>(_UnsignedByte_QNAME, Short.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "unsignedInt")
    public JAXBElement<Long> createUnsignedInt(Long value) {
        return new JAXBElement<Long>(_UnsignedInt_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "char")
    public JAXBElement<Integer> createChar(Integer value) {
        return new JAXBElement<Integer>(_Char_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "short")
    public JAXBElement<Short> createShort(Short value) {
        return new JAXBElement<Short>(_Short_QNAME, Short.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "guid")
    public JAXBElement<String> createGuid(String value) {
        return new JAXBElement<String>(_Guid_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "unsignedShort")
    public JAXBElement<Integer> createUnsignedShort(Integer value) {
        return new JAXBElement<Integer>(_UnsignedShort_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DataSet }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "DataSet")
    public JAXBElement<DataSet> createDataSet(DataSet value) {
        return new JAXBElement<DataSet>(_DataSet_QNAME, DataSet.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "decimal")
    public JAXBElement<BigDecimal> createDecimal(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_Decimal_QNAME, BigDecimal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "boolean")
    public JAXBElement<Boolean> createBoolean(Boolean value) {
        return new JAXBElement<Boolean>(_Boolean_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Duration }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "duration")
    public JAXBElement<Duration> createDuration(Duration value) {
        return new JAXBElement<Duration>(_Duration_QNAME, Duration.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "base64Binary")
    public JAXBElement<byte[]> createBase64Binary(byte[] value) {
        return new JAXBElement<byte[]>(_Base64Binary_QNAME, byte[].class, null, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "int")
    public JAXBElement<Integer> createInt(Integer value) {
        return new JAXBElement<Integer>(_Int_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "long")
    public JAXBElement<Long> createLong(Long value) {
        return new JAXBElement<Long>(_Long_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "anyURI")
    public JAXBElement<String> createAnyURI(String value) {
        return new JAXBElement<String>(_AnyURI_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Float }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "float")
    public JAXBElement<Float> createFloat(Float value) {
        return new JAXBElement<Float>(_Float_QNAME, Float.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "dateTime")
    public JAXBElement<XMLGregorianCalendar> createDateTime(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_DateTime_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Byte }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "byte")
    public JAXBElement<Byte> createByte(Byte value) {
        return new JAXBElement<Byte>(_Byte_QNAME, Byte.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "double")
    public JAXBElement<Double> createDouble(Double value) {
        return new JAXBElement<Double>(_Double_QNAME, Double.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QName }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "QName")
    public JAXBElement<QName> createQName(QName value) {
        return new JAXBElement<QName>(_QName_QNAME, QName.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "anyType")
    public JAXBElement<Object> createAnyType(Object value) {
        return new JAXBElement<Object>(_AnyType_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "string")
    public JAXBElement<String> createString(String value) {
        return new JAXBElement<String>(_String_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "Pincode", scope = FetchPinCode.class)
    public JAXBElement<String> createFetchPinCodePincode(String value) {
        return new JAXBElement<String>(_FetchPinCodePincode_QNAME, String.class, FetchPinCode.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "XML", scope = CreateOnlyReceipt.class)
    public JAXBElement<String> createCreateOnlyReceiptXML(String value) {
        return new JAXBElement<String>(_CreateOnlyReceiptXML_QNAME, String.class, CreateOnlyReceipt.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "CreatePolicyResult", scope = CreatePolicyResponse.class)
    public JAXBElement<String> createCreatePolicyResponseCreatePolicyResult(String value) {
        return new JAXBElement<String>(_CreatePolicyResponseCreatePolicyResult_QNAME, String.class, CreatePolicyResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "CreateOnlyReceiptResult", scope = CreateOnlyReceiptResponse.class)
    public JAXBElement<String> createCreateOnlyReceiptResponseCreateOnlyReceiptResult(String value) {
        return new JAXBElement<String>(_CreateOnlyReceiptResponseCreateOnlyReceiptResult_QNAME, String.class, CreateOnlyReceiptResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "XML", scope = CreateAndUpdateClient.class)
    public JAXBElement<String> createCreateAndUpdateClientXML(String value) {
        return new JAXBElement<String>(_CreateOnlyReceiptXML_QNAME, String.class, CreateAndUpdateClient.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "CreatePolicyWithPayerResult", scope = CreatePolicyWithPayerResponse.class)
    public JAXBElement<String> createCreatePolicyWithPayerResponseCreatePolicyWithPayerResult(String value) {
        return new JAXBElement<String>(_CreatePolicyWithPayerResponseCreatePolicyWithPayerResult_QNAME, String.class, CreatePolicyWithPayerResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "CreateAndUpdateCorporateClientResult", scope = CreateAndUpdateCorporateClientResponse.class)
    public JAXBElement<String> createCreateAndUpdateCorporateClientResponseCreateAndUpdateCorporateClientResult(String value) {
        return new JAXBElement<String>(_CreateAndUpdateCorporateClientResponseCreateAndUpdateCorporateClientResult_QNAME, String.class, CreateAndUpdateCorporateClientResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "policyno", scope = IsClaim.class)
    public JAXBElement<String> createIsClaimPolicyno(String value) {
        return new JAXBElement<String>(_IsClaimPolicyno_QNAME, String.class, IsClaim.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "XML", scope = CreateAndUpdateCorporateClient.class)
    public JAXBElement<String> createCreateAndUpdateCorporateClientXML(String value) {
        return new JAXBElement<String>(_CreateOnlyReceiptXML_QNAME, String.class, CreateAndUpdateCorporateClient.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "XML", scope = CreatePolicy.class)
    public JAXBElement<String> createCreatePolicyXML(String value) {
        return new JAXBElement<String>(_CreateOnlyReceiptXML_QNAME, String.class, CreatePolicy.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "Product", scope = CreatePolicy.class)
    public JAXBElement<String> createCreatePolicyProduct(String value) {
        return new JAXBElement<String>(_CreatePolicyProduct_QNAME, String.class, CreatePolicy.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "CreateAndUpdateClientResult", scope = CreateAndUpdateClientResponse.class)
    public JAXBElement<String> createCreateAndUpdateClientResponseCreateAndUpdateClientResult(String value) {
        return new JAXBElement<String>(_CreateAndUpdateClientResponseCreateAndUpdateClientResult_QNAME, String.class, CreateAndUpdateClientResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IsClaimResponse.IsClaimResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "isClaimResult", scope = IsClaimResponse.class)
    public JAXBElement<IsClaimResponse.IsClaimResult> createIsClaimResponseIsClaimResult(IsClaimResponse.IsClaimResult value) {
        return new JAXBElement<IsClaimResponse.IsClaimResult>(_IsClaimResponseIsClaimResult_QNAME, IsClaimResponse.IsClaimResult.class, IsClaimResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FetchPinCodeResponse.FetchPinCodeResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "FetchPinCodeResult", scope = FetchPinCodeResponse.class)
    public JAXBElement<FetchPinCodeResponse.FetchPinCodeResult> createFetchPinCodeResponseFetchPinCodeResult(FetchPinCodeResponse.FetchPinCodeResult value) {
        return new JAXBElement<FetchPinCodeResponse.FetchPinCodeResult>(_FetchPinCodeResponseFetchPinCodeResult_QNAME, FetchPinCodeResponse.FetchPinCodeResult.class, FetchPinCodeResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "multiReceipt", scope = CreatePolicyWithPayer.class)
    public JAXBElement<String> createCreatePolicyWithPayerMultiReceipt(String value) {
        return new JAXBElement<String>(_CreatePolicyWithPayerMultiReceipt_QNAME, String.class, CreatePolicyWithPayer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "PayerID", scope = CreatePolicyWithPayer.class)
    public JAXBElement<String> createCreatePolicyWithPayerPayerID(String value) {
        return new JAXBElement<String>(_CreatePolicyWithPayerPayerID_QNAME, String.class, CreatePolicyWithPayer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "XML", scope = CreatePolicyWithPayer.class)
    public JAXBElement<String> createCreatePolicyWithPayerXML(String value) {
        return new JAXBElement<String>(_CreateOnlyReceiptXML_QNAME, String.class, CreatePolicyWithPayer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "Product", scope = CreatePolicyWithPayer.class)
    public JAXBElement<String> createCreatePolicyWithPayerProduct(String value) {
        return new JAXBElement<String>(_CreatePolicyProduct_QNAME, String.class, CreatePolicyWithPayer.class, value);
    }

}
