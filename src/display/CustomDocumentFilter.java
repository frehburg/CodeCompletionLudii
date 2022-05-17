package display;

import javax.swing.*;
import javax.swing.text.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class CustomDocumentFilter extends DocumentFilter
{
//    private final JTextArea textArea;
//    private final StyledDocument styledDocument;
//
//    public CustomDocumentFilter(JTextArea textArea) {
//        this.textArea = textArea;
//        this.styledDocument= textArea.getStyledDocument();
//    }
//
//    private final StyleContext styleContext = StyleContext.getDefaultStyleContext();
//    private final AttributeSet greenAttributeSet = styleContext.addAttribute(styleContext.getEmptySet(), StyleConstants.Foreground, Color.GREEN);
//    private final AttributeSet blackAttributeSet = styleContext.addAttribute(styleContext.getEmptySet(), StyleConstants.Foreground, Color.BLACK);
//
//    // Use a regular expression to find the words you are looking for
//    Pattern pattern = buildPattern();
//
//    @Override
//    public void insertString(FilterBypass fb, int offset, String text, AttributeSet attributeSet) throws BadLocationException {
//        super.insertString(fb, offset, text, attributeSet);
//
//        handleTextChanged();
//    }
//
//    @Override
//    public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
//        super.remove(fb, offset, length);
//
//        handleTextChanged();
//    }
//
//    @Override
//    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attributeSet) throws BadLocationException {
//        super.replace(fb, offset, length, text, attributeSet);
//
//        handleTextChanged();
//    }
//
//    /**
//     * Runs your updates later, not during the event notification.
//     */
//    private void handleTextChanged()
//    {
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                updateTextStyles();
//            }
//        });
//    }
//
//    /**
//     * Build the regular expression that looks for the whole word of each word that you wish to find.  The "\\b" is the beginning or end of a word boundary.  The "|" is a regex "or" operator.
//     * @return
//     */
//    private Pattern buildPattern()
//    {
//        StringBuilder sb = new StringBuilder();
//        for (String token : ALL_WORDS_THAT_YOU_WANT_TO_FIND) {
//            sb.append("\\b"); // Start of word boundary
//            sb.append(token);
//            sb.append("\\b|"); // End of word boundary and an or for the next word
//        }
//        if (sb.length() > 0) {
//            sb.deleteCharAt(sb.length() - 1); // Remove the trailing "|"
//        }
//
//        Pattern p = Pattern.compile(sb.toString());
//
//        return p;
//    }
//
//
//    private void updateTextStyles()
//    {
//        // Clear existing styles
//        styledDocument.setCharacterAttributes(0, textArea.getText().length(), blackAttributeSet, true);
//
//        // Look for tokens and highlight them
//        Matcher matcher = pattern.matcher(textArea.getText());
//        while (matcher.find()) {
//            // Change the color of recognized tokens
//            styledDocument.setCharacterAttributes(matcher.start(), matcher.end() - matcher.start(), greenAttributeSet, false);
//        }
//    }
}
