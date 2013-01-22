package com.exadel.youtube.connector;

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
