package com.alienrfid;

import com.alien.enterpriseRFID.notify.Message;
import com.alien.enterpriseRFID.notify.MessageListener;
import com.alien.enterpriseRFID.tags.Tag;

/**
 * Created by guilherme.diniz on 4/17/17.
 */
public class AutoMessageListener implements MessageListener {
    @Override
    public void messageReceived(Message message) {
        Common.readCount++;
        if (message.getTagCount() == 0) {
            return;
        }
        for (Tag tag : message.getTagList()) {
            if (Common.tagsMap.containsKey(tag.getTagID())) {
                Common.tagsMap.put(tag.getTagID(), Common.tagsMap.get(tag.getTagID()) + 1);
            } else {
                Common.tagsMap.put(tag.getTagID(), 1);
            }

            System.out.println(tag.toLongString());
        }

    }
}
