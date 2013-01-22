package com.exadel.youtube.client;

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

