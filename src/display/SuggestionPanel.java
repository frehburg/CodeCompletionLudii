package display;

import codecompletion.Ludeme;
import codecompletion.controller.Controller;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import static codecompletion.domain.model.Preprocessing.INSERT_REC_WILDCARD;

public class SuggestionPanel {
    private final JTextArea textarea;
    private final int position;
    private final Controller controller;
    private JList list;
    private JPopupMenu popupMenu;
    private final int insertionPosition;

    public SuggestionPanel(JTextArea textarea, int position, Point location, Controller controller) {
        this.controller = controller;
        this.textarea = textarea;
        this.position = position;
        this.insertionPosition = position;
        popupMenu = new JPopupMenu();
        popupMenu.removeAll();
        popupMenu.setOpaque(false);
        popupMenu.setBorder(null);
        popupMenu.add(list = createSuggestionList(position), BorderLayout.CENTER);
        popupMenu.show(textarea, location.x, textarea.getBaseline(0, 0) + location.y);
    }

    public void hide(SuggestionPanel suggestion) {
        popupMenu.setVisible(false);
        if(suggestion == this) {
            suggestion = null;
        }
    }

    private JList createSuggestionList(final int position) {
        // create the contextString
        String contextString = textarea.getText().substring(0,position);
        String begunWord = "";
        //TODO change this to include the begunword
        //at the moment, cannot process words that have already begun, therefore cut them out of the context string
        char lastChar = textarea.getText().charAt(position - 1);
        if(lastChar != ' ') {
            //cut of begun word
            int lastSpacePosition = textarea.getText().lastIndexOf(' ');
            if(lastSpacePosition == -1) {
                lastSpacePosition = 0;
            }
            contextString = textarea.getText().substring(0,lastSpacePosition);
            begunWord = textarea.getText().substring(lastSpacePosition);
        }
        List<Ludeme> picklist = controller.getPicklist(contextString,10);
        Object[] data = picklist.toArray(new Ludeme[0]);
        JList list = new JList(data);
        list.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    insertSelection();
                }
            }
        });
        return list;
    }

    public boolean insertSelection() {
        if (list.getSelectedValue() != null) {
            try {
                final String selectedSuggestion = ((String) list.getSelectedValue().toString());
                textarea.getDocument().insertString(insertionPosition, selectedSuggestion + " ", null);
                hide(this);
                return true;
            } catch (BadLocationException e1) {
                e1.printStackTrace();
            }
        }
        return false;
    }

    public void moveUp() {
        int index = Math.min(list.getSelectedIndex() - 1, 0);
        selectIndex(index);
    }

    public void moveDown() {
        int index = Math.min(list.getSelectedIndex() + 1, list.getModel().getSize() - 1);
        selectIndex(index);
    }

    private void selectIndex(int index) {
        final int position = textarea.getCaretPosition();
        list.setSelectedIndex(index);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                textarea.setCaretPosition(position);
            };
        });
    }
}
