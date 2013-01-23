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

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

/**
 * class for showing info about videos
 * from youtube.com site to console
 *
 * @author Vladymyr Malykov
 */
public class YoutubeClient
{
    private final String HOST = "www.youtube.com";

    /**
     * Entry point
     */
    public static void main( String[] args )
    {
        YoutubeClient client = new YoutubeClient();
        URL url;
        if(args.length > 0) {
            try {
                url = client.getUrl(args[0]);
                client.showRelatedVideosInfo(url);

            } catch(IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

        } else {
           System.out.println("You should enter page address with videos from www.youtube.com as param!!!");
        }

    }

    /**
     * Show info about related videos from loaded content
     * to console
     *
     * @param _url youtube page address
     */
    public void showRelatedVideosInfo(URL _url) {
        YoutubeParser parser = new YoutubeParser();
        ArrayList<RelatedVideoItem> videos = (ArrayList<RelatedVideoItem>) parser.getRelatedVideosInfo(_url);
        if(videos == null) {
            System.out.println("Not found any related videos at " + _url);
        } else {
            int count = 0;
            for (RelatedVideoItem video : videos) {
                System.out.println(++count + ". " + video + "\n");
            }
        }
    }

    /**
     * Check for valid url and create java.net.URL instance
     *
     * @param _url page address with video
     *
     * @return java.net.URL instance with page address with video
     */
    private URL getUrl(String _url) {
        URL url;
        try {
            // validation _url
            url = URI.create(_url).toURL();
            if(!url.getHost().equals(HOST)) {
                throw new IllegalArgumentException("Url invalid!");
            }
        } catch(IllegalArgumentException ie) {
            // to explain the causes of exception
            throw new IllegalArgumentException("Url invalid!");
        } catch(MalformedURLException e) {
            throw new IllegalArgumentException("Url invalid!");
        }
        return url;
    }
}
