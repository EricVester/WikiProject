import Domain.Parser;
import Domain.RevisonParser;
import Domain.Revisor;
import Domain.WikiPageCheck;
import Exceptions.ConnectionCheck;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class UI extends JFrame implements ActionListener {
    Parser parser = new Parser();
    String lastRun = "";
    JLabel displayLabel;
    JButton wikiGet;
    JTextField getTopic;
    JTable displayTable;
    JScrollBar displayScroll;
    JButton getList;

    public UI(){
        super("Wikipedia Research");
        UIManager.put("Label.font", new FontUIResource(new Font("Dialog", Font.PLAIN, 20)));
        UIManager.put("Button.font", new FontUIResource(new Font("Dialog", Font.PLAIN, 20)));

        JPanel panel = new JPanel();
        GridBagLayout gridBagLayout= new GridBagLayout();
        panel.setLayout(gridBagLayout);
        setContentPane(panel);

         displayLabel = new JLabel("_");
        var displayLabelConstraints = new GridBagConstraints(0, 0, 15, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(20, 20, 20, 20), 0, 0);
        panel.add(displayLabel, displayLabelConstraints);

        getTopic = new JTextField();
        var  topicConstraints =   new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(1, 1, 1, 1), 0, 0);
        panel.add(getTopic,topicConstraints);

        getList = new JButton("Get Most Often Editor");
        var  getListConstraints =   new GridBagConstraints(0, 4, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(1, 1, 1, 1), 0, 0);
        getList.addActionListener(e -> {

            displayLabel.setText("Unavailable at this time");
        });
        panel.add(getList,getListConstraints);

        ConnectionCheck connectionCheck = new ConnectionCheck();
        if (connectionCheck.checkURL() == false) {
            displayLabel.setText("No connection to Wikipedia try again later");
        } else {
            displayLabel.setText("Connection Test passed you may continue and type a topic below");
        }

        wikiGet = new JButton("Get Revisions");
        var  wikiGetConstraints =   new GridBagConstraints(0, 3, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(1, 1, 1, 1), 0, 0);
        wikiGet.addActionListener(e -> {
            String topic = getTopic.getText();
            try {
                    ArrayList<Revisor> revisions = parser.parse(topic);
                    WikiPageCheck checkPage = new WikiPageCheck();

                    if (checkPage.checkExistence(revisions)) {
                        for (Revisor r : revisions) {
                            lastRun = lastRun + r.toString() + " ";
                            displayLabel.setText("<html>" + lastRun + "<html>");

                        }
                    } else {
                        displayLabel.setText("No Wikipedia Page found for this Topic");
                    }
                    lastRun = "";
                } catch(UnsupportedEncodingException ex){
                    ex.printStackTrace();
                } catch(MalformedURLException ex){
                    ex.printStackTrace();
                }
        });


        panel.add(wikiGet,wikiGetConstraints);

        setPreferredSize(new Dimension(400, 600));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);

    }
    public static void main(String[] args){
        new UI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}