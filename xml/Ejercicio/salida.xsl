<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : biblioteca.xsl
    Created on : 27 de noviembre de 2007, 12:13
    Author     : user
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html" encoding="UTF-8"/>
    
    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/">
        <html>
            <head>
                <title>salida.html</title>
            </head>
            <body>
                <table border="1" cellspacing="3" cellpadding="5">
                    <thead>
                        <tr>
                            <th>Teléfono</th>
                        </tr>
                    </thead>
                    <tbody>
                        <xsl:apply-templates select="/telefonos/telefono"/>
                    </tbody>
                </table>
            </body>
        </html>
    </xsl:template>
    <xsl:template match="telefono">
        <tr>
            <td><xsl:value-of select="@numero"/> </td>
        </tr>
    </xsl:template>  
</xsl:stylesheet>
