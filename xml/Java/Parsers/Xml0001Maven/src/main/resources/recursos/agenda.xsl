<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : agenda.xsl
    Created on : 25 de noviembre de 2007, 20:09
    Author     : user
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>
    
    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/">
        <html>
            <head>
                <title>agenda.xsl</title>
            </head>
            <body>
                <xsl:apply-templates select="/agenda/contacto[@clase='personal']"/>
            </body>
        </html>
    </xsl:template>
    <xsl:template match="contacto">
        Tipo de contacto: <i><xsl:value-of select="@clase"/></i><br/>
        Nombre del contacto: <strong><xsl:value-of select="nombre"/></strong><br/>
        Teléfono del contacto: <xsl:value-of select="telefono"/>
    </xsl:template>
</xsl:stylesheet>
