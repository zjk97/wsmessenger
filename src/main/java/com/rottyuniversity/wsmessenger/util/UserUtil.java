package com.rottyuniversity.wsmessenger.util;

import com.rottyuniversity.wsmessenger.model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class UserUtil {
    public static String getChatRoomIdByUsers(List<User> users) {
        List<String> userIds = users
                .stream()
                .map((user -> user.getId()))
                .collect(Collectors.toList());

        return getChatRoomIdByUserIds(userIds);
    }

    public static String getChatRoomIdByUserIds(List<String> userIds) {
        Collections.sort(userIds);
        StringBuilder sb = new StringBuilder();
        Iterator<String> iterator = userIds.iterator();

        for (int i = 0; i < userIds.size() - 1; i++) {
            sb
                    .append(iterator.next().replace("_", "__"))
                    .append(" _ ");
        }

        if (iterator.hasNext()) {
            sb.append(iterator.next());
        }

        return sb.toString();
    }

    public static List<String> getUserIdsByChatRoomId(String chatRoomId) {
        String[] split = chatRoomId.split(" _ ", -1);
        List<String> ret = new ArrayList<>(split.length);

        for (int i = 0; i < split.length; i++) {
            ret.add(split[i].replace("__", "_"));
        }

        return ret;
    }
}
