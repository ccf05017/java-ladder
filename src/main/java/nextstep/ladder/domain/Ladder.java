package nextstep.ladder.domain;

import nextstep.ladder.domain.exceptions.InvalidLadderHeightException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Ladder {
    private static final int MIN_HEIGHT = 1;
    private List<Line> lines;

    private Ladder(List<Line> lines) {
        this.lines = new ArrayList<>(lines);
    }

    public static Ladder create(int ladderHeight, int countOfPerson, PointAddStrategy pointAddStrategy) {
        validateLadderHeight(ladderHeight);
        List<Line> lines = new ArrayList<>();
        IntStream.range(0, ladderHeight)
                .forEach(num ->
                        lines.add(LineFactory.create(countOfPerson, pointAddStrategy)));
        return new Ladder(lines);
    }

    public int getHeight() {
        return lines.size();
    }

    public List<Line> getLines() {
        return new ArrayList<>(this.lines);
    }

    private static void validateLadderHeight(int ladderHeight) {
        if (ladderHeight < MIN_HEIGHT) {
            throw new InvalidLadderHeightException("Ladder height must exceed zero");
        }
    }
}
