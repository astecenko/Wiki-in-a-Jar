<?xml version="1.0" encoding="ISO-8859-1"?>

<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template match="tag-list">

		<xsl:if test="count(tag) &gt; 0">
			<div id="tag-list">
				tagged as
				<xsl:for-each select="tag">
					<a>
						<xsl:attribute name="href">
							/tag/tree/
							<xsl:value-of select="." />
						</xsl:attribute>
						<xsl:value-of select="." />
					</a>
					&#160;
				</xsl:for-each>
			</div>
		</xsl:if>
	</xsl:template>

</xsl:stylesheet>