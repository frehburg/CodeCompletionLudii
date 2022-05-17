package display;

import utils.FileUtils;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ColorDocumentFilter extends DocumentFilter
{
    private final JTextPane textPane;
    private final StyledDocument styledDocument;
    private final boolean lightMode;

    private StyleContext styleContext;
    private AttributeSet ludemeAttributeSet,parenthesisAttributeSet, attributeAttributeSet, defaultAttributeSet,
            numberAttributeSet, stringAttributeSet, mathAttributeSet, bracesAttributeSet, hashAttributeSet,
            optionAttributeSet,commentAttributeSet;
    private Pattern ludemePattern, parenthesisPattern, attributePattern, numberPattern, stringPattern, mathPattern,
            bracesPattern, hashPattern, optionPattern,commentPattern;


    public ColorDocumentFilter(JTextPane textPane, boolean lightMode) {
        this.lightMode = lightMode;
        this.textPane = textPane;
        this.styledDocument= textPane.getStyledDocument();
        init();
    }

    /**
     * Initialization
     */
    private void init() {
        styleContext = StyleContext.getDefaultStyleContext();
        ludemeAttributeSet = styleContext.addAttribute(styleContext.getEmptySet(), StyleConstants.Foreground, Color.decode("#CC7832"));
        parenthesisAttributeSet = styleContext.addAttribute(styleContext.getEmptySet(), StyleConstants.Foreground, Color.decode("#CC7832"));
        attributeAttributeSet = styleContext.addAttribute(styleContext.getEmptySet(), StyleConstants.Foreground, Color.decode("#9876AA"));
        numberAttributeSet = styleContext.addAttribute(styleContext.getEmptySet(), StyleConstants.Foreground, Color.decode("#6897BB"));
        stringAttributeSet = styleContext.addAttribute(styleContext.getEmptySet(), StyleConstants.Foreground, Color.decode("#6a8759"));
        mathAttributeSet = styleContext.addAttribute(styleContext.getEmptySet(), StyleConstants.Foreground, Color.decode("#BBB529"));
        bracesAttributeSet = styleContext.addAttribute(styleContext.getEmptySet(), StyleConstants.Foreground, Color.decode("#BBB529"));
        hashAttributeSet = styleContext.addAttribute(styleContext.getEmptySet(), StyleConstants.Foreground, Color.decode("#707D95"));
        optionAttributeSet = styleContext.addAttribute(styleContext.getEmptySet(), StyleConstants.Foreground, Color.decode("#507874"));
        commentAttributeSet = styleContext.addAttribute(styleContext.getEmptySet(), StyleConstants.Foreground, Color.decode("#7F7F7A"));

        defaultAttributeSet = styleContext.addAttribute(styleContext.getEmptySet(), StyleConstants.Foreground, Color.decode("#575757"));


        // Use a regular expression to find the words you are looking for
        parenthesisPattern = Pattern.compile("\\(|\\)");
        ludemePattern = buildPattern(getLudemeRegex());
        attributePattern = buildPattern(getAttributeRegex());
        numberPattern = buildPattern(getNumberRegex());
        stringPattern = Pattern.compile("\"[^\"]*\"|\"[^\"]*|\"");
        mathPattern = Pattern.compile("!=|%|\\*|\\+|-|/|<|<=|=|>|>=|\\^");
        bracesPattern = Pattern.compile("\\{|}");
        hashPattern = Pattern.compile("#.*");
        optionPattern = Pattern.compile("<.*>");
        commentPattern = Pattern.compile("//.*");
    }

    @Override
    public void insertString(FilterBypass fb, int offset, String text, AttributeSet attributeSet) throws BadLocationException {
        super.insertString(fb, offset, text, attributeSet);
        //--------
        handleTextChanged();
    }

    @Override
    public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
        super.remove(fb, offset, length);

        handleTextChanged();
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attributeSet) throws BadLocationException {
        super.replace(fb, offset, length, text, attributeSet);

        handleTextChanged();
    }

    /**
     * Runs your updates later, not during the event notification.
     */
    private void handleTextChanged()
    {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                updateTextStyles();
            }
        });
    }

    /**
     * Build the regular expression that looks for the whole word of each word that you wish to find.  The "\\b" is the beginning or end of a word boundary.  The "|" is a regex "or" operator.
     * @return
     * @param regex
     */
    private Pattern buildPattern(List<String> regex)
    {
        StringBuilder sb = new StringBuilder();
        for (String token : regex) {
            sb.append("\\b"); // Start of word boundary
            sb.append(token);
            sb.append("\\b|"); // End of word boundary and an or for the next word
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1); // Remove the trailing "|"
        }

        Pattern p = Pattern.compile(sb.toString());

        return p;
    }


    private void updateTextStyles()
    {
        // Clear existing styles
        styledDocument.setCharacterAttributes(0, textPane.getText().length(), defaultAttributeSet, true);

        List<Pattern> patterns = Arrays.asList(new Pattern[]{ludemePattern,parenthesisPattern,attributePattern,
                numberPattern,stringPattern,bracesPattern,hashPattern,optionPattern, commentPattern,mathPattern});
        List<AttributeSet> attributeSets = Arrays.asList(new AttributeSet[]{ludemeAttributeSet,parenthesisAttributeSet,
                attributeAttributeSet,numberAttributeSet,stringAttributeSet,mathAttributeSet,bracesAttributeSet,
                hashAttributeSet,optionAttributeSet, commentAttributeSet});
        // Look for tokens and highlight them
        for(int i = 0; i < patterns.size(); i++) {
            Pattern pattern = patterns.get(i);
            AttributeSet attributeSet = attributeSets.get(i);
            Matcher matcher = pattern.matcher(textPane.getText());
            while (matcher.find()) {
                // Change the color of recognized tokens
                styledDocument.setCharacterAttributes(matcher.start(), matcher.end() - matcher.start(), attributeSet, true);
            }
        }
    }

    private List<String> getLudemeRegex() {
        List<String> ludemes = new ArrayList<>();
        Scanner sc = FileUtils.readFile("res/highlighting/allLudemes.txt");
        while (sc.hasNext()) {
            String nextLine = sc.nextLine();
            ludemes.add(nextLine);
        }
        sc.close();
        return ludemes;
    }
    private List<String> getAttributeRegex() {
        List<String> attributes = new ArrayList<>();
        Scanner sc = FileUtils.readFile("res/highlighting/allAttributes.txt");
        while (sc.hasNext()) {
            String nextLine = sc.nextLine();
            attributes.add(nextLine);
        }
        sc.close();
        return attributes;
    }

    private List<String> getNumberRegex() {
        List<String> numbers = new ArrayList<>();
        Scanner sc = FileUtils.readFile("res/highlighting/allAttributes.txt");
        numbers.add("[0-9]*\\.[0-9]*");
        numbers.add("[0-9]*\\.");
        numbers.add("[0-9]*");
        return numbers;
    }
}
