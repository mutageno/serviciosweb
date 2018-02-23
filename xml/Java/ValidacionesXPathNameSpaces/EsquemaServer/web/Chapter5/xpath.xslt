<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="xml" omit-xml-declaration="yes"/>
<xsl:template match="/catalog/journal">
Date: <xsl:value-of select="article[title='Advance DAO Programming']/@date"/>
Title: <xsl:value-of select="article[author='Srikanth Shenoy']/title"/>
</xsl:template>
</xsl:stylesheet>

