<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : biblioteca.xsl
    Created on : 27 de noviembre de 2007, 12:13
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
                <title>biblioteca.xsl</title>                
            </head>
            <body>
                <table border="1" cellspacing="3" cellpadding="5">
                    <thead>
                        <tr>
                            <th>Isbn</th>
                            <th>Título</th>
                            <th>Autor</th>
                            <th>Precio</th>
                        </tr>
                    </thead>
                    <tbody>
                        <xsl:apply-templates select="/biblioteca/libro"/>
                    </tbody>
                </table>
            </body>
        </html>
    </xsl:template>
    <xsl:template match="libro">
        <tr>
            <td><xsl:value-of select="@isbn"/> </td>        
            <td><xsl:value-of select="titulo"/> </td>
            <td><xsl:value-of select="autor"/> </td>
            <td><xsl:value-of select="precio"/> </td>
        </tr>
    </xsl:template>  
</xsl:stylesheet>
