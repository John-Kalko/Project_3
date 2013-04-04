<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : XmlToXml.xsl
    Created on : 14 Декабрь 2012 г., 6:42
    Author     : Калько Евгений
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="xml"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    
    <xsl:template match="/">
        <tarrifes_containter>
            <payroll>
                <xsl:apply-templates/>
            </payroll>
        </tarrifes_containter>
    </xsl:template>
    

   
    
    <!--<xsl:template match="/">
        <ns1:tariffes_container>
            <xsl:element name="tariff_name">
                <xsl:attribute name="tariff_name">
                    <xsl:value-of select="@tariff_name"/>
                </xsl:attribute>
                <ns1:operator1>
                    <xsl:value-of select="operator1"/>
                </ns1:operator1>
                <ns1:operator2>
                    <xsl:value-of select="operator2"/>
                </ns1:operator2>
                <ns1:operator3>
                    <xsl:value-of select="operator3"/>
                </ns1:operator3>
                <ns1:payroll>
                    <xsl:value-of select="payroll"/>
                </ns1:payroll>
                <call_prices>
                    <xsl:attribute name="inside_network">
                        <xsl:value-of select="@inside_network"/>
                    </xsl:attribute>
                    <xsl:attribute name="another_network">
                        <xsl:value-of select="@another_network"/>
                    </xsl:attribute>
                    <xsl:attribute name="outside_network">
                        <xsl:value-of select="@outside_network"/>
                    </xsl:attribute>
                </call_prices>
                <sms_price>
                    <xsl:value-of select="sms_price"/>
                </sms_price>
                <xsl:element name="additional_prices">
                    <discount_numbers>
                        <xsl:value-of select="additional_prices/discount_numbers"/>
                    </discount_numbers>
                    <tarrifiaction_type>
                        <xsl:value-of select="additional_prices/tarrifiaction_type"/>
                    </tarrifiaction_type>
                    <tariff_price>
                        <xsl:value-of select="additional_prices/tariff_price"/>
                    </tariff_price>
                    <xsl:apply-templates/>
                </xsl:element>        
            </xsl:element>
        </ns1:tariffes_container>
    </xsl:template>-->
</xsl:stylesheet>
