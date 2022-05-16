package display;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SuggestionPanel {
    private final JTextArea textarea;
    private final int position;
    private JList list;
    private JPopupMenu popupMenu;
    private final int insertionPosition;

    public SuggestionPanel(JTextArea textarea, int position, Point location) {
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
        //TODO: add recs here
        Object[] data = new String[] {"(game","(equipment","Add","(to","(sites","Empty"};
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
                final String selectedSuggestion = ((String) list.getSelectedValue());
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
