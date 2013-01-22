package com.exadel.youtube.client;

/**
 * class for store info about related video
 */
public class RelatedVideoItem {
    private String title;
    private String rating;
    private String address;

    public RelatedVideoItem(String _address, String _title, String _rating) {
        address = _address;
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