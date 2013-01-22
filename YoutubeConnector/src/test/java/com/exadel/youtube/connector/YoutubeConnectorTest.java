package com.exadel.youtube.connector;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.htmlcleaner.TagNode;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

/**
 * Unit test for YoutubeConnector.
 */
public class YoutubeConnectorTest extends TestCase {
    /*
     * Create the test case
     *
     * @param testName name of the test case
     */
    public YoutubeConnectorTest( String testName ) {
        super( testName );
    }

    /*
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite( YoutubeConnectorTest.class );
    }

    public void testGetRootValidUrl() {
        URL url = null;
        try {
            url = new URL("http://www.youtube.com/watch?v=wcqLWisCJcU");
        } catch (MalformedURLException e) {
        }
        YoutubeConnector connector = new YoutubeConnector();
        TagNode root = connector.getRoot(url);
        assertTrue( root != null );
    }
}
