/*
 * Wiki in a jar
 * 
 * Copyright (C) 2007 rico_g AT users DOT sourceforge DOT net
 * 
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License s published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 51
 * Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
 * 
 */
package org.rgse.wikiinajar.helpers.wiki.render;

import org.rgse.wikiinajar.helpers.TextUtils;
import org.rgse.wikiinajar.models.WikiArticle;
import org.rgse.wikiinajar.views.tagtree.ShowTagTreeView;
import org.rgse.wikiinajar.views.vcard.ShowVcardView;
import org.rgse.wikiinajar.views.wiki.EditWikiArticleView;
import org.rgse.wikiinajar.views.wiki.ShowWikiArticleView;

/**
 * Represents a wiki style link. A link has three components: an optional
 * namespace, the required page name, and an optional link name.
 * 
 * <pre>
 * Example:
 * 
 * [[tagtree:  /all/wiki|All wiki pages]]  is split into:
 * - namespace: &quot;tagtree:
 * - page name: &quot;/all/wiki&quot;
 * - link name: &quot;All wiki pages&quot;
 * </pre>
 * 
 * <p>
 * Additionally, a wiki link can be invisible, meaning it won't be embedded in
 * the actual page (e.g. tags).
 * </p>
 * 
 * 
 * @author rico_g AT users DOT sourceforge DOT net
 * 
 */
public class WikiLink {

	private static final String NAMESPACE_SEP = ":";

	public static final String WIKI_NAMESPACE = "wiki";

	public static final String VCARD_NAMESPACE = "vcard";

	public static final String TAGS_NAMESPACE = "tags";

	public static final String TAG_TREE_NAMESPACE = "tagtree";

	public static final String SIDEBAR_NAMESPACE = "sidebar";

	private static final String LINK_NAME_SEP = "|";

	private String link;

	private String namespace;

	private String htmlLink;

	private String linkName;

	public WikiLink(String plainLink, String defaultNamespace) {
		// reverse html escaping
		plainLink = plainLink.replaceAll("&amp;", "&");
		String[] tokens = splitWikiLink(plainLink, defaultNamespace);
		this.namespace = tokens[0];
		this.link = tokens[1];
		this.linkName = tokens[2];
		this.htmlLink = determineHtmlLink();
	}

	/**
	 * Based on the namespace, figures out the link. No namespace results in a
	 * wiki link (since there is no link support for vcards yet).
	 * 
	 * @return The link or empty string for hidden links.
	 */
	private String determineHtmlLink() {
		String result = "";
		String encLink = TextUtils.escapeHtmlChars(link);
		String encName = TextUtils.escapeHtmlChars(linkName);
		if ((WIKI_NAMESPACE.equals(namespace) || VCARD_NAMESPACE
				.equals(namespace))
				&& (containsIllegalChars(namespace) || containsIllegalChars(link))) {
			result = "[[" + encLink
					+ "]] (Link must not contain: \\*/:?\"&lt;&gt;|) ";
		} else if (WIKI_NAMESPACE.equalsIgnoreCase(this.namespace)) {
			if (WikiArticle.exists(link)) {
				result = makeUrl(ShowWikiArticleView.getLink(link), encName,
						namespace, encLink, false);
			} else {
				result = makeUrl(EditWikiArticleView.getLink(link), encName,
						namespace, encLink, true);
			}
		} else if (VCARD_NAMESPACE.equalsIgnoreCase(namespace)) {
			result = makeUrl(ShowVcardView.getLink(link), encName, namespace,
					link, false);

		} else if (TAG_TREE_NAMESPACE.equalsIgnoreCase(namespace)) {
			result = makeUrl(ShowTagTreeView.getLink(link), encName, namespace,
					encLink, false);
		} else if (TAGS_NAMESPACE.equalsIgnoreCase(namespace)) {
			// hide tags
		} else if (SIDEBAR_NAMESPACE.equalsIgnoreCase(namespace)) {
			// hide sidebar
		} else {
			result = encLink + " (Unknown namespace: " + namespace + ") ";
		}
		return result;
	}

	private String makeUrl(String target, String linkName, String namespace,
			String pageId, boolean isEditLink) {
		String title = namespace + ": " + pageId
				+ (isEditLink ? " (edit)" : "");

		return "<a " + (isEditLink ? "id=\"editlink\"" : "") + " href=\""
				+ target + "\" title=\"" + title + "\" >" + linkName + "</a>";
	}

	/**
	 * Because we use the page title as file name, certain characters are not
	 * allowed. This checks for these characters.
	 * 
	 * @param path
	 * @return <code>true</code> if the path contains characters that are
	 *         illegal in file names.
	 */
	private boolean containsIllegalChars(String path) {
		for (int i = 0; i < path.length(); i++) {
			switch (path.charAt(i)) {
			case '\\':
			case '/':
			case ':':
			case '*':
			case '?':
			case '"':
			case '<':
			case '>':
			case '|':
				return true;
			}
		}
		return false;
	}

	/**
	 * No-op constructor for testing.
	 * 
	 */
	protected WikiLink() {
	}

	/**
	 * Splits a wiki link in up to 3 components: namespace, page name, link
	 * name. For example:
	 * 
	 * <pre>
	 * [[tagtree:  /all/wiki|All wiki pages]]  is split into:
	 * - namespace: &quot;tagtree:
	 * - page name: &quot;/all/wiki&quot;
	 * - link name: &quot;All wiki pages&quot;
	 * </pre>
	 * 
	 * @param plainLink
	 *            The plain wiki formated link.
	 * @param defaultNamespace
	 *            The default namespace to use.
	 * 
	 * @return String array with 3 elements: namespace (0, defaults to default
	 *         namespace), page name (1), link name (2, defaults to page name).
	 */
	protected String[] splitWikiLink(String plainLink, String defaultNamespace) {
		String ns = null;
		String pageName = null;
		String linkName = null;

		int index = plainLink.indexOf(NAMESPACE_SEP);
		if (index != -1) {
			ns = plainLink.substring(0, index).trim();
			plainLink = plainLink.substring(index + 1).trim();
		} else {
			ns = defaultNamespace;
		}
		index = plainLink.indexOf(LINK_NAME_SEP);
		if (index != -1) {
			pageName = plainLink.substring(0, index).trim();
			linkName = plainLink.substring(index + 1).trim();
		} else {
			pageName = plainLink.trim();
			linkName = pageName;
		}
		// quick sanity check:
		linkName = linkName.length() > 0 ? linkName : pageName;
		ns = ns.length() > 0 ? ns: defaultNamespace;
		
		return new String[] { ns, pageName, linkName };
	}

	/**
	 * Returns the HTML code for this link. If the link is invisible this
	 * returns an empty string.
	 * 
	 * @return
	 */
	public String toHtml() {
		return htmlLink;
	}

	public String getPlainLink() {
		return link;
	}

	public String getNamespace() {
		return namespace;
	}

}
