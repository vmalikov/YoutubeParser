package com.exadel.youtube.parser;

/*
 * #%L
 * YoutubeClient
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2013 Malykov Vladymyr
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */

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
    private final String UL_ID = "watch-related";
    private final String UL_NAME = "ul";

    private final String LI_CLASS = "video-list-item";
    private final String LI_NAME = "li";

    private final String HREF_NAME = "href";
    private final String SPAN_NAME = "span";
    private final String CLASS_NAME = "class";
    private final String ID_NAME = "id";

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
            TagNode ul_elem = getVideoElem(root);
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
    private TagNode getVideoElem(TagNode root) {
        TagNode elements[] = root.getElementsByName(UL_NAME, true);
        for (int i = 0; elements != null && i < elements.length; i++) {
            String classType = elements[i].getAttributeByName(CLASS_NAME);
            String idType = elements[i].getAttributeByName(ID_NAME);
            if (classType != null && classType.equals(UL_CLASS) && idType != null && idType.equals(UL_ID)) {
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