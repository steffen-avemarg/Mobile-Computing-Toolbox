/**
 * DBInterface.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.emgress.backend.friedolin;

public interface DBInterface extends java.rmi.Remote {
    public java.lang.String process(java.lang.String system, java.lang.String body) throws java.rmi.RemoteException;
    public java.lang.String process(java.lang.String system, java.lang.String user, java.lang.String pass, java.lang.String body) throws java.rmi.RemoteException;
    public java.lang.String getData(java.lang.String strName, java.lang.String strID) throws java.rmi.RemoteException;
    public java.lang.String getDataByParams(java.lang.String strName, java.lang.String xmlParams) throws java.rmi.RemoteException;
    public java.lang.String getDataByParams(java.lang.String strName, java.lang.String strID, java.lang.String xmlParams) throws java.rmi.RemoteException;
    public java.lang.String getDataXML(java.lang.String xmlParams) throws java.rmi.RemoteException;
    public java.lang.String getDataXMLByAuthUser(java.lang.String xmlParams) throws java.rmi.RemoteException;
    public java.lang.String getDataSearch(java.lang.String elParams) throws java.rmi.RemoteException;
    public java.lang.String SOAPDataImporter(java.lang.String strXMLStructure, java.lang.String strUser, java.lang.String strPassword) throws java.rmi.RemoteException;
}
