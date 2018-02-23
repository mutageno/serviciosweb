<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="xml" omit-xml-declaration="yes"/>
<xsl:template match="/">
<xsl:element name="journal">
<xsl:attribute name="publisher">
<xsl:text>IBM developerWorks</xsl:text>
</xsl:attribute>
</xsl:element>
</xsl:template>
</xsl:stylesheet>

