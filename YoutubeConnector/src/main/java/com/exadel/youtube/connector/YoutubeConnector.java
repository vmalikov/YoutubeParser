package com.exadel.youtube.connector;

/*
 * #%L
 * YoutubeConnector
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

import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

/**
 * class for getting html page from specific address
 * as org.htmlcleaner.TagNode class instance
 *
 * @author Vladymyr Malykov
 */
public class YoutubeConnector
{
    private final String TAG = "Youtube parser connector";

    /**
    * Create instance TagNode class which represented root node of html
    * document loaded from specified URL.
    *
    * @author Vladymyr Malykov
    *
    * @param url instance java.net.URL class with web address
    *
    * @return instance org.htmlcleaner.TagNode class which represented root
    * node in html document loaded from specified URL.
    */
    public TagNode getRoot(URL url) {
        System.out.println(TAG + " in getRoot()");
        HtmlCleaner cleaner = new HtmlCleaner();
        TagNode root = null;
        try {
            root = cleaner.clean(url);
        } catch (IOException e) {
            System.out.println("IOException in " + TAG + ". Can't create root element from " + e.getMessage());
        }
        return root;
    }
}
