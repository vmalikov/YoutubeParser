package com.exadel.youtube.client;

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

import com.exadel.youtube.parser.YoutubeParser;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.omg.IOP.TAG_ALTERNATE_IIOP_ADDRESS;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Unit test for YoutubeParser.
 */
public class YoutubeParserTest extends TestCase {
    private final String TAG = "YoutubeParserTest";

    /*
     * Create the test case
     *
     * @param testName name of the test case
     */
    public YoutubeParserTest(String testName) {
        super( testName );
    }

    /*
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite( YoutubeParserTest.class );
    }

    public void testGetRelatedVideosInfo() {
        URL url = null;
        try {
            url = new URL("http://www.youtube.com/watch?v=wcqLWisCJcU");
        } catch (MalformedURLException e) {
            System.out.println("Invalid url in " + TAG + " testGetRelatedVideosInfo()");}
        YoutubeParser parser = new YoutubeParser();
        ArrayList<RelatedVideoItem> videos = (ArrayList<RelatedVideoItem>) parser.getRelatedVideosInfo(url);
        for(RelatedVideoItem video : videos) {
            if(video == null) {
                fail("RelatedVideoItem can't be null!");
            }
        }
    }

    public void testGetRelatedVideosInfoCount() {
        URL url = null;
        try {
            url = new URL("http://www.youtube.com/watch?v=wcqLWisCJcU");
        } catch (MalformedURLException e) {
            System.out.println("Invalid url in " + TAG + " testGetRelatedVideosInfoCount()");
        }
        YoutubeParser parser = new YoutubeParser();
        ArrayList<RelatedVideoItem> videos = (ArrayList<RelatedVideoItem>) parser.getRelatedVideosInfo(url);
        assertTrue(videos.size() >= 20);
    }
}

