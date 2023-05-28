import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main extends JFrame {

    public Main() {
        setTitle("GSM Network");
        setSize(1200, 480);

        SenderPanel senderPanel = new SenderPanel();
        senderPanel.setPreferredSize(new Dimension(getWidth() / 5, getHeight()));

        BasesSectionPanel basesSectionPanel = new BasesSectionPanel();
        basesSectionPanel.setPreferredSize(new Dimension(getWidth() / 3 * 5, getHeight()));

        RecipientPanel recipientPanel = new RecipientPanel();
        recipientPanel.setPreferredSize(new Dimension(getWidth() / 5, getHeight()));

        add(senderPanel, BorderLayout.WEST);
        add(basesSectionPanel, BorderLayout.CENTER);
        add(recipientPanel, BorderLayout.EAST);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);


        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

                try (FileOutputStream fos = new FileOutputStream("VBD.txt")) {

//                    fos.write();


                    System.out.println("Data written to the file successfully.");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                dispose();

            }
        });
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
//        Texts t   = new Texts();
//        Thread t1 = new Author(t);
//        Thread t2 = new Publisher(t);
//        t1.start();
//        t2.start();
    }
}

//class Texts {
//    private String txt = null;
//    private boolean newTxt = false;
//    // invoked by Author to set a new text
//    synchronized public void setText(String s) {
//        while (newTxt) { // not if!!!
//            try {
//                wait();
//            } catch(InterruptedException exc) {}
//    }
//        txt = s;
//        newTxt = true;
//        notify(); // invoked on 'this'
//    }
//    synchronized public String getText() {
//        while (!newTxt) { // not if!!!
//        try {
//            wait(); // invoked on 'this'
//        } catch(InterruptedException exc) {}
//    }
//        newTxt = false;
//        notify(); // invoked on 'this' return txt;
//        return txt;
//    }
//}
//class Publisher extends Thread {
//    private Texts txtArea;
//    public Publisher(Texts t) {
//        txtArea=t;
//    }
//    public void run() {
//        String txt = null;
//        while ((txt = txtArea.getText()) != null) {
//            System.out.println("-> " + txt);
//        }
//    }
//}
//class Author extends Thread {
//    private Texts txtArea;
//
//    public Author(Texts t) {
//        txtArea = t;
//    }
//
//    public void run() {
//        String[] texts = {"Hamlet", "War and Peace",
//                "Macbeth", "The Trial", "Crime and Punishment",
//                "Madame Bovary", null};
//        for (int i = 0; i < texts.length; i++) {
//            try {   // writing a book takes some time...
//                sleep((int) (1500 + Math.random() * 300));
//            } catch (InterruptedException ignored) {
//            }
//            txtArea.setText(texts[i]);
//        }
//    }
//}


