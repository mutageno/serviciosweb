<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xalan="http://xml.apache.org/xslt"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="xml" xalan:indent-amount="3"
 indent="yes"/>
<xsl:template match="/">
<xsl:copy-of select="catalog"/>
</xsl:template>
</xsl:stylesheet>

