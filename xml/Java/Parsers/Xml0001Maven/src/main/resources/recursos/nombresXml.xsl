<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml" indent="yes"/>
    <xsl:template match="/">
        <xsl:element name="nombres">
            <xsl:apply-templates select="/agenda/contacto"/>        
        </xsl:element>
    </xsl:template>
    <xsl:template match="contacto">
        <xsl:element name="nombre">
            <xsl:value-of select="nombre"/>
        </xsl:element>
    </xsl:template>
</xsl:stylesheet>