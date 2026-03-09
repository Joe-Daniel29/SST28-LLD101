package com.example.map;

/**
 * Context object in the Flyweight pattern.
 * Holds only extrinsic state (lat, lng, label) specific to each marker.
 * Intrinsic state (style) is a reference to a shared MarkerStyle flyweight.
 */
public class MapMarker {

    private final double lat;
    private final double lng;
    private final String label;

    // Shared flyweight — not owned, just referenced
    private final MarkerStyle style;

    public MapMarker(double lat, double lng, String label, MarkerStyle style) {
        this.lat = lat;
        this.lng = lng;
        this.label = label;
        this.style = style; // shared instance from MarkerStyleFactory
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public String getLabel() {
        return label;
    }

    public MarkerStyle getStyle() {
        return style;
    }
}
