package com.alienrfid;

import com.alien.enterpriseRFID.reader.AlienClass1Reader;
import com.alien.enterpriseRFID.reader.AlienReaderException;
import com.alien.enterpriseRFID.tags.Tag;

/**
 * Created by guilherme.diniz on 4/10/17.
 */
public class RFIDCommunication {

    private AlienClass1Reader reader;
    private long readCount = 0;

    /**
     * Constructor
     */
    public RFIDCommunication(String connect, String user, String password) throws AlienReaderException {
        this.reader = new AlienClass1Reader();

        reader.setConnection(connect, 23);
        reader.setUsername("alien");
        reader.setPassword("password");
        reader.open();
    }

    public void setAutomateMode(String notAddr) throws AlienReaderException {
        reader.setNotifyAddress(notAddr + ":4000");
        reader.setNotifyFormat(AlienClass1Reader.XML_FORMAT);
        reader.setNotifyTime(15000);
        reader.autoModeReset();
        reader.setAutoMode(AlienClass1Reader.ON);
        reader.setNotifyTrigger("TrueFalse"); // Notify whether there's a tag or not
        reader.setNotifyMode(AlienClass1Reader.ON);

    }

    public Tag[] read() throws AlienReaderException {
        readCount++;
        return  reader.getTagList();
    }

    public void restart() {
        readCount = 0;
    }

    public void closer() {
        reader.close();
    }

    public long getReadCount() {
        return readCount;
    }
}
