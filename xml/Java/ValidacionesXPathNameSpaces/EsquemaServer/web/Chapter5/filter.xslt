<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="xml" omit-xml-declaration="yes"/>
<xsl:template match="/catalog/journal">
<xsl:apply-templates select="article[@level='Intermediate']"/>
</xsl:template>
<xsl:template match="article">
Title: <xsl:value-of select="title"/>
Author: <xsl:value-of select="author"/>
</xsl:template>
</xsl:stylesheet>

