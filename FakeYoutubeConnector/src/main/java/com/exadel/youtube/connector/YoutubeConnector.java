package com.exadel.youtube.connector;

/*
 * #%L
 * FakeYoutubeConnector
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

import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

/**
 * class for getting fake html page from file at "YoutubuParser/page.html"
 * as org.htmlcleaner.TagNode class instance
 *
 * @author Vladymyr Malykov
 */
public class YoutubeConnector
{
    private final String tag = "Youtube parser fake connector";

    // YoutubeParser/files/page.html
    private String path = "files/page.html";

    /**
    * Create instance TagNode class which represented root node of html
    * document loaded from specified URL.
    *
    * @param url instance java.net.URL class with web address
    *
    * @return instance org.htmlcleaner.TagNode class which represented root
    * node in html document loaded from specified URL.
    */
    public TagNode getRoot(URL url) {
        System.out.println(tag + " in getRoot()");
        HtmlCleaner cleaner = new HtmlCleaner();

        String full_path = getFullPath(path);

        File file = new File(full_path);
        FileReader reader = null;
        if(file.exists()) {
            try {
                reader = new FileReader(file);
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }  else {
            throw new IllegalArgumentException(file.getAbsolutePath() + " doesn't exist!");
        }
        TagNode root = null;
        try {
            root = cleaner.clean(reader);
        } catch (IOException e) {
            System.out.println("Exception!!! " + e.getMessage());
        }
        return root;
    }

    /**
     * method build full path to file with fake content
     *
     * @author Vladymyr Malykov
     *
     * @param path to file with fake content
     * @return full path to file with fake content
     */
    private String getFullPath(String path) {
        File directory = new File ("");
        String builder = directory.getAbsolutePath();
        String dir = builder.substring(0, builder.lastIndexOf("YoutubeParser") + "YoutubeParser".length());
        return dir + File.separator + path;
    }
}
