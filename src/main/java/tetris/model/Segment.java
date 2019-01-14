package main.java.tetris.model;

import java.awt.Point;

public class Segment {

    private final Point points[];
    private final SegmentType type;
    private final boolean initialOrientation;

    private Segment(SegmentType segmentType, Point[] points, boolean initial) {
        initialOrientation = initial;
        this.points = points;
        this.type = segmentType;
    }

    public static Segment getRandomPiece() {
        SegmentType segmentType = SegmentType.getRandomPiece();
        return new Segment(segmentType, segmentType.getPoints(), true);
    }


    public SegmentType getType() {
        return type;
    }

    public Point[] getPoints() {
        return points;
    }

    public Segment rotate() {
        if (type.getMaxOrientations() == 0) {
            return this;
        } else if (type.getMaxOrientations() == 2) {
            if (initialOrientation) {
                return new Segment(type, rotateRight(points), false);
            } else {
                return new Segment(type, rotateLeft(points), true);
            }
        }
        return new Segment(type, rotateRight(points), true);
    }

    private Point[] rotateLeft(Point toRotate[]) {
        return rotate(toRotate, 1, -1);
    }

    private Point[] rotateRight(Point toRotate[]) {
        return rotate(toRotate, -1, 1);
    }

    private Point[] rotate(Point toRotate[], int x, int y) {
        Point rotated[] = new Point[4];

        for (int i = 0; i < 4; i++) {
            int temp = toRotate[i].x;
            rotated[i] = new Point(x * toRotate[i].y, y * temp);
        }

        return rotated;
    }

}