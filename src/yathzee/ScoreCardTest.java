package yathzee;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScoreCardTest {

    @Test
    public void smallStraightShouldBeDetected() {

        ScoreCard scoreCard = new ScoreCard();

        ArrayList<Integer> diceValues = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 5));

        ArrayList<ScoreInterface> scoreOptions = scoreCard.findPossibleScores(diceValues);

        assertEquals(1,2);

    }

}