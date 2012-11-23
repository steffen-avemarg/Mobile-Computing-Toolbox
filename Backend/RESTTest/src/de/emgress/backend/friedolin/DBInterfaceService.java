/**
 * DBInterfaceService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.emgress.backend.friedolin;

public interface DBInterfaceService extends javax.xml.rpc.Service {
    public java.lang.String getdbinterfaceAddress();

    public DBInterface getdbinterface() throws javax.xml.rpc.ServiceException;

    public DBInterface getdbinterface(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
