<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : XmlToXml.xsl
    Created on : 14.12.12
    Author     : Kalko I
    Description:
        Patern for creating xml with another root element from source xml
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
                xmlns:ns1="http://www.tariff.com/XSD">
    <xsl:output method="xml"/>

    <!-- Root template -->
    <xsl:template match="/">
        <ns1:tariff_container>
            <xsl:apply-templates/>
        </ns1:tariff_container>
    </xsl:template>
    
    <!-- Single element template -->
    <xsl:template match="ns1:tariff_name">
        <xsl:element name="ns1:tariff_name">
            <xsl:attribute name="tariff_name">
                <xsl:value-of select="@tariff_name"/>
            </xsl:attribute>
            <xsl:if test="ns1:operator1">
                <xsl:element name="ns1:operator1">
                    <xsl:value-of select="ns1:operator1"/>
                </xsl:element>
            </xsl:if>
            <xsl:if test="ns1:operator2">
                <xsl:element name="ns1:operator2">
                    <xsl:value-of select="ns1:operator2"/>
                </xsl:element>
            </xsl:if>
            <xsl:if test="ns1:operator3">
                <xsl:element name="ns1:operator3">
                    <xsl:value-of select="ns1:operator3"/>
                </xsl:element>
            </xsl:if>
            <xsl:element name="ns1:payroll">
                <xsl:value-of select="ns1:payroll"/>
            </xsl:element>
            <xsl:element name="ns1:call_prices">
                <xsl:attribute name="inside_network">
                    <xsl:value-of select="ns1:call_prices/@inside_network"/>
                </xsl:attribute>
                <xsl:attribute name="another_network">
                    <xsl:value-of select="ns1:call_prices/@another_network"/>
                </xsl:attribute>
                <xsl:attribute name="outside_network">
                    <xsl:value-of select="ns1:call_prices/@outside_network"/>
                </xsl:attribute>
            </xsl:element>
            <xsl:element name="ns1:sms_price">
                <xsl:value-of select="ns1:sms_price"/>
            </xsl:element>
            <xsl:element name="ns1:additional_prices">
                <xsl:element name="ns1:discount_numbers">
                    <xsl:value-of select="ns1:additional_prices/ns1:discount_numbers"/>
                </xsl:element>
                <xsl:element name="ns1:tarrifiaction_type">
                    <xsl:value-of select="ns1:additional_prices/ns1:tarrifiaction_type"/>
                </xsl:element>
                <xsl:element name="ns1:tariff_price">
                    <xsl:value-of select="ns1:additional_prices/ns1:tariff_price"/>
                </xsl:element>
            </xsl:element>
        </xsl:element>
    </xsl:template>
</xsl:stylesheet>
