package com.alienrfid;

import com.alien.enterpriseRFID.reader.AlienClass1Reader;
import com.alien.enterpriseRFID.reader.AlienReaderException;
import com.alien.enterpriseRFID.tags.Tag;

/**
 * Created by guilherme.diniz on 4/10/17.
 */
public class RFIDCommunication {

    /**
     * Constructor
     */
    public RFIDCommunication() throws AlienReaderException {

        AlienClass1Reader reader = new AlienClass1Reader();
//  reader.setConnection("COM1");

        // To connect to a networked reader instead, use the following:
        reader.setConnection("150.164.10.42", 23);
        reader.setUsername("alien");
        reader.setPassword("password");

        reader.setNotifyAddress("150.164.0.249:4000");
        reader.setNotifyTime(30);
        reader.autoModeReset();
        reader.setAutoMode(AlienClass1Reader.ON);


        // Open a connection to the reader
        reader.open();

        // Ask the reader to read tags and print them
        Tag tagList[] = reader.getTagList();
        if (tagList == null) {
            System.out.println("No Tags Found");
        } else {
            System.out.println("Tag(s) found:");
            for (int i=0; i<tagList.length; i++) {
                Tag tag = tagList[i];
                System.out.println("ID:" + tag.getTagID() +
                        ", Discovered:" + tag.getDiscoverTime() +
                        ", Last Seen:" + tag.getRenewTime() +
                        ", Antenna:" + tag.getAntenna() +
                        ", Reads:" + tag.getRenewCount()
                );
            }
        }

        // Close the connection
        reader.close();
    }

    /**
     * Main
     */
    public static final void main(String args[]){
        try {
            new RFIDCommunication();
        } catch(AlienReaderException e) {
            System.out.println("Error: " + e.toString());
        }
    }

}
