package com.rottyuniversity.wsmessenger.util;

import com.rottyuniversity.wsmessenger.model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class UserUtil {
    public static String getChatRoomIdByUsers(List<User> users) {
        Collections.sort(users, Comparator.comparing(User::getId));
        StringBuilder sb = new StringBuilder();
        Iterator<User> iterator = users.iterator();

        for (int i = 0; i < users.size() - 1; i++) {
            sb
                    .append(iterator.next().getId().replace("_", "__"))
                    .append(" _ ");
        }

        if (iterator.hasNext()) {
            sb.append(iterator.next().getId());
        }

        return sb.toString();
    }

    public static List<String> getUserIdsByChatRoomId(String chatRoomId) {
        String[] split = chatRoomId.split(" _ ", -1);
        List<String> ret = new ArrayList<>(split.length);

        for (int i=0; i<split.length; i++) {
            ret.add(split[i].replace("__", "_"));
        }

        return ret;
    }
}
