package com.exadel.youtube.client;

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
        if(args.length != 0) {
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
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Url invalid!");
        }
        return url;
    }
}
