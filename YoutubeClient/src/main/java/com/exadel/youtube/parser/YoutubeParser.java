package com.exadel.youtube.parser;

import com.exadel.youtube.client.RelatedVideoItem;
import com.exadel.youtube.connector.YoutubeConnector;

import org.htmlcleaner.TagNode;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * class for parsing html page from youtube.com
 * and getting info about related videos
 */
public class YoutubeParser {

    private final String CLASS_RATTING = "stat view-count";
    private final String TITLE = "title";

    private final String UL_CLASS = "video-list";
    private final String UL_NAME = "ul";

    private final String LI_CLASS = "video-list-item";
    private final String LI_NAME = "li";

    private final String HREF_NAME = "href";
    private final String SPAN_NAME = "span";
    private final String CLASS_NAME = "class";

    /**
     * Create list with all related videos from the content loaded
     * from specified URL.
     *
     * @param url java.net.URL with html page address
     *
     * @return list with related videos items
     */
    public List<RelatedVideoItem> getRelatedVideosInfo(URL url) {
        YoutubeConnector connector = new YoutubeConnector();
        TagNode root = connector.getRoot(url);
        if (root != null) {
            TagNode ul_elem = findVideoListElem(root);
            if (ul_elem != null) {
                return getRelatedVideosList(ul_elem);
            }
        }
        return null;
    }

    /**
     * Find <ul> element which contains all <li> elements with info about
     * related videos.
     *
     * @param root instance org.htmlcleaner.TagNode class which represented root
     * node in html document loaded from specified URL.
     *
     * @return instance org.htmlcleaner.TagNode class which represent <ul>
     * element with all <li> elements with info about related videos
     */
    private TagNode findVideoListElem(TagNode root) {
        TagNode elements[] = root.getElementsByName(UL_NAME, true);
        for (int i = 0; elements != null && i < elements.length; i++) {
            String classType = elements[i].getAttributeByName(CLASS_NAME);
            if (classType != null && classType.equals(UL_CLASS)) {
                return elements[i];
            }
        }
        return null;
    }

    /**
     * create list with info about related videos from loaded content
     * which found at param ul_elem
     *
     * @param ul_elem org.htmlcleaner.TagNode instance where info about related videos
     *
     * @return list with com.exadel.youtube.client.RelatedVideoItem instances with info about videos
     */
    private List<RelatedVideoItem> getRelatedVideosList(TagNode ul_elem) {
        ArrayList<RelatedVideoItem> videos = new ArrayList<RelatedVideoItem>();
        TagNode elements[] = ul_elem.getElementsByName(LI_NAME, true);
        for (int i = 0; elements != null && i < elements.length; i++) {
            String classType = elements[i].getAttributeByName(CLASS_NAME);
            // it means that elements[i] is tag <li> which needed
            if (classType != null && classType.equals(LI_CLASS)) {
                RelatedVideoItem item = getRelatedVideoInfo(elements[i]);
                videos.add(item);
            }
        }
        return videos;
    }

    /**
     * create instance of com.exadel.youtube.client.RelatedVideoItem class with values which
     * found in li_elem
     *
     * @param li_elem org.htmlcleaner.TagNode instance from loaded content
     *
     * @return created instance of com.exadel.youtube.client.RelatedVideoItem class with values from li_elem
     */
    private RelatedVideoItem getRelatedVideoInfo(TagNode li_elem) {
        // find <a> tag in <li>
        TagNode a_elem = li_elem.findElementHavingAttribute(HREF_NAME, true);
        // address
        String address = a_elem.getAttributeByName(HREF_NAME);
        String title = "";
        String ratting = "";
        TagNode spanElements[] = a_elem.getElementsByName(SPAN_NAME, true);
        for (int k = 0; spanElements != null && k < spanElements.length; k++) {
            String type = spanElements[k].getAttributeByName(CLASS_NAME);
            if (type != null && type.equals(TITLE)) {
                // title
                title = spanElements[k].getAttributeByName(TITLE);
            } else if (type != null && type.equals(CLASS_RATTING)) {
                // ratting
                ratting = formatRating(spanElements[k].getText());
            }
        }
        return new RelatedVideoItem(address, title, ratting);
    }

    /**
     * Getting the number from string obtained from the loaded html document
     *
     * @param ratting string from loaded content
     *
     * @return ratting video from param ratting as string
     */
    private String formatRating(StringBuffer ratting) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < ratting.length(); i++) {
            char c = ratting.charAt(i);
            if (Character.isDigit(c)) {
                builder.append(c);
            }
        }
        return builder.toString();
    }
}