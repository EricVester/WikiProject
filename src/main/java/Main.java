import Domain.*;
import Exceptions.ConnectionCheck;

import java.net.URL;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;



public class Main {


    public static void main(String[] args) throws IOException {
        ConnectionCheck connectionCheck = new ConnectionCheck();
        if (connectionCheck.checkURL() == false) {
            System.out.println("No connection to Wikipedia try again later");
        } else {
            System.out.println("Connection Test passed you may continue");

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("What topic would you like to see the recent revisions for in Wikipedia?");
            String topic = br.readLine();
            Scanner scanner = new Scanner(topic);
            Parser parser = new Parser();
            ArrayList<Revisor> revisions = parser.parse(topic);

            WikiPageCheck checkPage = new WikiPageCheck();

            if (checkPage.checkExistence(revisions)) {
                for (Revisor r : revisions) {
                    System.out.println(r);
                }
            } else {
                System.out.println("No Wikipedia Page found for this Topic");
            }

            System.out.println("Second List printed");

            RevisionSorter revisionSorter = new RevisionSorter();
            ArrayList<Revisor> revisionSorted = revisionSorter.sort(revisions);
            System.out.println(revisionSorted);



        }
    }

    }


