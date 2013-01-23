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

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.htmlcleaner.TagNode;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

/**
 * Unit test for FakeYoutubeConnector.
 */
public class YoutubeConnectorTest extends TestCase {
    private final String TAG = "fake YoutubeConnectorTest";

    public YoutubeConnectorTest( String testName ) {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite( YoutubeConnectorTest.class );
    }

    public void testGetRoot() {
        URL url = null;
        try {
            url = new URL("http://www.youtube.com/watch?v=wcqLWisCJcU");
        } catch (MalformedURLException e) {
            System.out.println("Invalid url in " + TAG + " testGetRoot()");
        }
        YoutubeConnector connector = new YoutubeConnector();
        TagNode root = connector.getRoot(url);
        assertTrue( root != null );
    }
}
