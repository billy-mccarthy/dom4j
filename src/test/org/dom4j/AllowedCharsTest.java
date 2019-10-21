package org.dom4j;


import junit.framework.TestCase;

/**
 * @author Filip Jirsák
 */
public class AllowedCharsTest extends TestCase {

    public void testLocalName() {
        QName.get("element");
        QName.get(":element");
        QName.get("elem:ent");
    }

    public void testLocalNameFail() {
        try {
            QName.get("!element");
            fail("Expected IllegalArgumentException.class but no exception occurred.");
        } catch (IllegalArgumentException e) {
        }
    }

    public void testQname() {
        QName.get("element", "http://example.com/namespace");
        QName.get("ns:element", "http://example.com/namespace");
    }

    public void testQnameFail1() {
        try {
            QName.get("ns:elem:ent", "http://example.com/namespace");
            fail("Expected IllegalArgumentException.class but no exception occurred.");
        } catch (IllegalArgumentException e) {
        }
    }


    public void testQnameFail2() {
        try {
            QName.get(":nselement", "http://example.com/namespace");
            fail("Expected IllegalArgumentException.class but no exception occurred.");
        } catch (IllegalArgumentException e) {
        }
    }

    public void tryCreateElementLT() {
        try {
            DocumentHelper.createElement("element<name");
            fail("Expected IllegalArgumentException.class but no exception occurred.");
        } catch (IllegalArgumentException e) {
        }
    }

    public void testCreateElementGT() {
        try {
            DocumentHelper.createElement("element>name");
            fail("Expected IllegalArgumentException.class but no exception occurred.");
        } catch (IllegalArgumentException e) {
        }
    }

    public void testCreateElementAmpersand() {
        try {
            DocumentHelper.createElement("element&name");
            fail("Expected IllegalArgumentException.class but no exception occurred.");
        } catch (IllegalArgumentException e) {
        }
    }

    public void testAddElement() {
        try {
            Element root = DocumentHelper.createElement("root");
            root.addElement("element>name");
            fail("Expected IllegalArgumentException.class but no exception occurred.");
        } catch (IllegalArgumentException e) {
        }
    }

    public void testAddElementQualified() {
        try {
            Element root = DocumentHelper.createElement("root");
            root.addElement("element>name", "http://example.com/namespace");
            fail("Expected IllegalArgumentException.class but no exception occurred.");
        } catch (IllegalArgumentException e) {
        }
    }

    public void testAddElementQualifiedPrefix() {
        try {
            Element root = DocumentHelper.createElement("root");
            root.addElement("ns:element>name", "http://example.com/namespace");
            fail("Expected IllegalArgumentException.class but no exception occurred.");
        } catch (IllegalArgumentException e) {
        }
    }

    public void testAddElementPrefix() {
        try {
            Element root = DocumentHelper.createElement("root");
            root.addElement("ns>:element", "http://example.com/namespace");
            fail("Expected IllegalArgumentException.class but no exception occurred.");
        } catch (IllegalArgumentException e) {
        }
    }

    //TODO It is illegal to create element or attribute with namespace prefix and empty namespace IRI.
    //See https://www.w3.org/TR/2006/REC-xml-names11-20060816/#scoping
}
