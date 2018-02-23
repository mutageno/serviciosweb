<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                version="1.0">
    <xsl:output method="html"/>
    <xsl:template match="/">
        <html>
            <head>
                <title>Catálogo</title>
            </head>
            <body>
                <xsl:apply-templates />
            </body>
        </html>
    </xsl:template>
    <xsl:template match="catalog/journal/article">
        <p>
            Título:
            <xsl:value-of select="title" />
        </p>
    </xsl:template>
</xsl:stylesheet>
