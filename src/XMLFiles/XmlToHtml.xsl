<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : XmlToHtml.xsl
    Created on : 14.12.12
    Author     : Kalko I.
    Description:
        Patern for creating html from xml
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
                xmlns:ns1="http://www.tariff.com/XSD">
    <xsl:output method="html"/>

    <!-- Root template. Creates single html page -->
    <xsl:template match="/">
        <html>
            <head>
                <title>Prices of Ukrainian mobile operator tariffs</title>
            </head>
            <body>
                <h1 align="center">Prices of Ukrainian mobile operator tariffs</h1>
                <table border="1" align="center">
                    <tr align="center" bgcolor="ff8000">
                    <td>Tariff name</td>
                    <td>Operator</td>
                    <td>Payroll</td>
                    <td>Calling inside network</td>
                    <td>Calling to another network</td>
                    <td>Calling outside network</td>
                    <td>SMS price</td>
                    <td>No. of discount numbers</td>
                    <td>Tarrification type</td>
                    <td>Price</td>
                </tr>
                    <xsl:apply-templates/>
                </table>
            </body>
        </html>
    </xsl:template>
    <xsl:template match="ns1:tariff_name">
        <tr align="center">
            <td><xsl:value-of select="@tariff_name"/></td>
            <xsl:if test="ns1:operator1">
                <td>
                    <xsl:value-of select="ns1:operator1"/>
                </td>
            </xsl:if>
            <xsl:if test="ns1:operator2">
                <td>
                    <xsl:value-of select="ns1:operator2"/>
                </td>
            </xsl:if>
            <xsl:if test="ns1:operator3">
                <td>
                    <xsl:value-of select="ns1:operator3"/>
                </td>
            </xsl:if>
            <td>
                <xsl:value-of select="ns1:payroll"/>
            </td>
                <td>
                    <xsl:value-of select="ns1:call_prices/@inside_network"/>
                </td>
                <td>
                    <xsl:value-of select="ns1:call_prices/@another_network"/>
                </td>
                <td>
                    <xsl:value-of select="ns1:call_prices/@outside_network"/>
                </td>
            <td>
                <xsl:value-of select="ns1:sms_price"/>
            </td>
                <td>
                    <xsl:value-of select="ns1:additional_prices/ns1:discount_numbers"/>
                </td>
                <td>
                    <xsl:value-of select="ns1:additional_prices/ns1:tarrifiaction_type"/>
                </td>
                <td>
                    <xsl:value-of select="ns1:additional_prices/ns1:tariff_price"/>
                </td>
        </tr>
    </xsl:template>
</xsl:stylesheet>
