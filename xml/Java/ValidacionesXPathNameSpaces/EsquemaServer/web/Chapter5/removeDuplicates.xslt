<xsl:stylesheet version="1.0" 
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:output method="xml" version="1.0" omit-xml-declaration="yes"/>
  <xsl:template match="/">
    <xsl:variable name="unique-list" select="//title[not(.=following::
      title)]/text()" />
   <xsl:for-each select="$unique-list">
 <xsl:copy>
<xsl:apply-templates/>
</xsl:copy>
<xsl:text disable-output-escaping="yes">&#13; </xsl:text>
   </xsl:for-each>
  </xsl:template>
</xsl:stylesheet>
