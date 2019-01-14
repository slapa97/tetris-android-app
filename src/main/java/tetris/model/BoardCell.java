package main.java.tetris.model;

import java.util.Arrays;

public class BoardCell {

    private final SegmentType segmentType;

    private BoardCell() {
        segmentType = null;
    }

    private BoardCell(SegmentType type) {
        segmentType = type;
    }

    public boolean isEmpty() {
        return segmentType == null;
    }

    public SegmentType getSegmentType() {
        return segmentType;
    }

    public static BoardCell getCell(SegmentType segmentType) {
        return new BoardCell(segmentType);
    }

    public static BoardCell[] getEmptyArray(int size) {
        BoardCell[] cells = new BoardCell[size];
        Arrays.fill(cells, new BoardCell());
        return cells;
    }

}
