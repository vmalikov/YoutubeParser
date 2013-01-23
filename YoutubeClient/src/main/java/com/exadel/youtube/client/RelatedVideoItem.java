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

/**
 * class for store info about related video
 */
public class RelatedVideoItem {
    private String title;
    private String rating;
    private String address;

    private final String YOUTUBE_ADDRESS = "http://www.youtube.com";

    public RelatedVideoItem(String _address, String _title, String _rating) {
        address = YOUTUBE_ADDRESS + _address;
        title = _title;
        rating = _rating;
    }

    /**
     * override function for print to console
     * @return content video as String
     */
    public String toString() {
        return address + "\n   " + title + "\n   " + rating;
    }
}