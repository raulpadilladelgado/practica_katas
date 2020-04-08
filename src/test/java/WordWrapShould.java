import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/* TO DO LIST
    "hola", 7 -> "hola"
    "hola", 2 -> "ho\nla"
    "hola mundo", 7 -> "hola\n mundo"
    "null", 2 -> ""
    "", 2 -> ""
    "hola", -3 -> ThrowException
 */
public class WordWrapShould {
    @Test
    public void result_must_be_empty() {
        assertThat(wordWrap("", 2)).isEqualTo("");
        assertThat(wordWrap(null, 2)).isEqualTo("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void result_must_be_Ilegal_Argument_Exception() {
        wordWrap("hola", 0);
    }

    @Test
    public void text_must_be_wrap() {
        assertThat(wordWrap("hola", 2)).isEqualTo("ho\nla");
        assertThat(wordWrap("hola", 3)).isEqualTo("hol\na");
        assertThat(wordWrap("hola mundo", 6)).isEqualTo("hola\nmundo");
        assertThat(wordWrap("hola mundodffdfdfdfdf", 6)).isEqualTo("hola\nmundodf\nfdfdfd\nfdf");
        assertThat(wordWrap("holaquetalestas", 2)).isEqualTo("ho\nla\nqu\net\nal\nes\nta\ns");
        assertThat(wordWrap("holaquetalestas", 3)).isEqualTo("hol\naqu\neta\nles\ntas");
    }

    private String wordWrap(String text, int columnWidth) {
        if (columnWidth < 1) {
            throw new IllegalArgumentException("Column width must be one or more");
        }
        if (text == null) {
            return "";
        }

        boolean textIsGreaterThanColumnWidth = text.length() > columnWidth;
        if (textIsGreaterThanColumnWidth) {
            String result = "";
            for (int currentPosition = 0; currentPosition < text.length(); currentPosition += columnWidth) {
                boolean currentPositionIsLessThanRemainderText = currentPosition < text.length() - columnWidth;
                if (currentPositionIsLessThanRemainderText) {
                    result += wrapText(text, columnWidth, currentPosition);
                } else {
                    String remainder = text.substring(currentPosition); 
                    result += remainder;
                }
            }
            return result;
        }
        
        return text;
    }

    

    private String wrapText(String text, int columnWidth, int currentPosition) {
        String result;
        result = text.substring(currentPosition, currentPosition + columnWidth);
        int whiteSpaceIndex = result.indexOf(" ");
        if (whiteSpaceIndex != -1) {
            result = result.replace(" ", "\n");
        } else {
            result += "\n";
        }
        return result;
    }
}
