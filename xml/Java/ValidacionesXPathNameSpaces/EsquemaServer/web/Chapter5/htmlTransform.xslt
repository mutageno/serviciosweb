<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" 
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output   method="html"/>
<xsl:template match="/catalog/journal">
<html>
  <head>
    <title>Catalog</title>
  </head>
  <body>
    <table border="1" cellspacing="0">
        <tr>
         <th>Level</th>
         <th>Date</th>
         <th>Section</th>
         <th>Title</th>
         <th>Author</th>
        </tr>
      <xsl:for-each select="article">
        <tr>
         <td><xsl:value-of select="@
           level"/></td>
         <td><xsl:value-of select="
          @date"/></td>
         <td><xsl:value-of select="@
          section"/></td>
         <td><xsl:value-of select="title"
           /></td>
         <td><xsl:value-of select="author"
           /></td>
        </tr>
      </xsl:for-each>
    </table>
  </body>
</html>
</xsl:template>
</xsl:stylesheet>
