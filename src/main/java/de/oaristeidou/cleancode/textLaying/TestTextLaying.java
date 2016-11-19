package de.oaristeidou.cleancode.textLaying;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * Created by odyssefs on 06.11.16.
 */
public class TestTextLaying {

    private static final String TEST_TEXT =
            "Es blaut die Nacht,\n" +
                    "die Sternlein  blinken,\n" +
                    "Schneeflöcklein  leis hernieder sinken.";
    private static final String CLEANTEXT =
            "Es blaut die Nacht, " +
                    "die Sternlein blinken, " +
                    "Schneeflöcklein leis hernieder sinken.";
    private static final String CLEANED_TEXT = "Es blaut die Nacht, die Sternlein blinken, Schneeflöcklein leis hernieder sinken.";
    private static final String[] CLEANED_TEXT_ARRAY = {
            "Es", "blaut", "die", "Nacht,", "die", "Sternlein", "blinken,", "Schneeflöcklein", "leis", "hernieder", "sinken."
    };
    private static final String TEST_TEXT_RESULT =
            "Es blaut die\n" +
                    "Nacht, die\n" +
                    "Sternlein\n" +
                    "blinken,\n" +
                    "Schneeflöcklei\n" +
                    "n leis\n" +
                    "hernieder\n" +
                    "sinken.";

    @Test(dataProvider = "dataTextLaying")
    public void testTextLaying(String text, int maxParagraphLegth, String expected) {
        assertEquals(text, maxParagraphLegth, expected);
    }

    @Test(dataProvider = "dataCleanText")
    public void testCleanText(String text, String expected) {
        assertEquals(TextLaying.cleanText(text), expected);
    }

    @Test(dataProvider = "dataSplitTextToWords")
    public void testSplitTextToWords(String cleanText, String expected[]) {
        assertEquals(TextLaying.splitTextToWords(cleanText), expected);
    }

    @Test(dataProvider = "dataApplyLineLayout")
    public void testApplyLineLayout(String words[], int maxParagraphLegth, List<String> expected) {
        assertEquals(TextLaying.applyLineLayout(words, maxParagraphLegth), expected);
    }

    @Test(dataProvider = "dataAppendLinesToText")
    public void testAppendLinesToText(List<String> lines, String expected) {
        assertEquals(TextLaying.appendLinesToText(lines), expected);
    }

    @DataProvider
    public Object[][] dataApplyLineLayout() {
        return new Object[][]{
                {CLEANED_TEXT_ARRAY, Integer.valueOf(13), getListOfLinesText()}
        };
    }

    @DataProvider
    public Object[][] dataAppendLinesToText() {
        return new Object[][]{
                {getListOfLinesText(), TEST_TEXT_RESULT}
        };
    }

    private List getListOfLinesText() {
        List linesOfText = new ArrayList<String>();
        linesOfText.add("Es blaut die\n");
        linesOfText.add("Nacht, die\n");
        linesOfText.add("Sternlein\n");
        linesOfText.add("blinken,\n");
        linesOfText.add("Schneeflöcklei\n");
        linesOfText.add("n leis\n");
        linesOfText.add("hernieder\n");
        linesOfText.add("sinken.");
        return linesOfText;
    }

    @DataProvider
    public Object[][] dataSplitTextToWords() {
        return new Object[][]{
                {CLEANED_TEXT, CLEANED_TEXT_ARRAY}
        };
    }

    @DataProvider
    public Object[][] dataCleanText() {

        return new Object[][]{
                {TEST_TEXT, CLEANTEXT}
        };
    }

    @DataProvider
    public Object[][] dataTextLaying() {

        return new Object[][]{
                {TEST_TEXT, Integer.valueOf(13), TEST_TEXT_RESULT}
        };
    }
}
