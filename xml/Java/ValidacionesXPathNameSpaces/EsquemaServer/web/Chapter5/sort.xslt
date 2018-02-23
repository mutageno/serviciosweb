<xsl:stylesheet 
xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
     version="1.0">
  <xsl:output method="xml" omit-xml-declaration="yes"/>
  <xsl:template match="/catalog/journal">
    <xsl:apply-templates>
      <xsl:sort select="title" 
       order="ascending"/>
    </xsl:apply-templates>
  </xsl:template>
<xsl:template match="article">
  Title:   <xsl:apply-templates select="title"/>
  </xsl:template>
</xsl:stylesheet>
