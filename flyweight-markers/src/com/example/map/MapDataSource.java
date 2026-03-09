package com.example.map;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Generates markers for demo/testing.
 *
 * NOW STATE (REFACTORED):
 * - Owns a single MarkerStyleFactory.
 * - Obtains shared MarkerStyle instances via factory.get() — no per-marker
 * allocation.
 * - Passes the shared style directly to the MapMarker constructor.
 */
public class MapDataSource {

    private static final String[] SHAPES = { "PIN", "CIRCLE", "SQUARE" };
    private static final String[] COLORS = { "RED", "BLUE", "GREEN", "ORANGE" };
    private static final int[] SIZES = { 10, 12, 14, 16 };

    private final MarkerStyleFactory styleFactory = new MarkerStyleFactory();

    public List<MapMarker> loadMarkers(int count) {
        Random rnd = new Random(7);
        List<MapMarker> out = new ArrayList<>(count);

        for (int i = 0; i < count; i++) {
            double lat = 12.9000 + rnd.nextDouble() * 0.2000;
            double lng = 77.5000 + rnd.nextDouble() * 0.2000;
            String label = "M-" + i;

            String shape = SHAPES[rnd.nextInt(SHAPES.length)];
            String color = COLORS[rnd.nextInt(COLORS.length)];
            int size = SIZES[rnd.nextInt(SIZES.length)];
            boolean filled = rnd.nextBoolean();

            // Obtain shared flyweight from factory
            MarkerStyle style = styleFactory.get(shape, color, size, filled);
            out.add(new MapMarker(lat, lng, label, style));
        }
        return out;
    }

    public MarkerStyleFactory getStyleFactory() {
        return styleFactory;
    }
}
