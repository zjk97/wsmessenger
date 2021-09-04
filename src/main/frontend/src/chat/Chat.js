import React, {useEffect, useRef} from "react";
import useState from "react-usestateref";
import "./Chat.css"
import ChatBox from "./ChatBox";
import OpenedChats from "./OpenedChats";
import axios from "axios";
import MessageBox from "./MessageBox";

function Chat(props) {
    const [displayedMessages, setDisplayedMessages] = useState([]);
    const [selectedChat, setSelectedChat, selectedChatRef] = useState("");
    const [openedChats, setOpenedChats, openedChatsRef] = useState(["Loading..."]);

    let stompClient = useRef(null);
    let currentUser = useRef(localStorage.getItem("currentUser"));
    let allMessages = useRef(new Map());
    const localMessageLimit = 30;

    // load opened chats only once, provide empty dependency array
    useEffect(() => {
        axios.post('/user/v1/openedChats?userId=' + currentUser.current).then(r => {
            setOpenedChats(r.data);
        });
    }, []);

    useEffect(() => {
        if (localStorage.getItem("currentUser") == null) {
            props.history.push("/login");
        } else {
            connect();
        }
    }, []);

    const connect = () => {
        if (stompClient.current == null) {
            const stomp = require("stompjs");
            var SockJS = require("sockjs-client");
            SockJS = new SockJS("http://192.168.0.165:8080/ws");
            // SockJS = new SockJS("/ws");
            stompClient.current = stomp.over(SockJS);
        }
        stompClient.current.connect({}, onConnected, onError);
    }

    const onConnected = () => {
        console.log("connected");
        console.log(currentUser.current);
        stompClient.current.subscribe(
            "/user/" + currentUser.current + "/queue/messages",
            onMessageReceived
        );
    }

    const onError = (e) => {
        alert(e)
        console.log("error");
        console.log(e);
    }

    const onMessageReceived = (message) => {
        console.log("received");
        const body = JSON.parse(message.body);
        const content = body["content"];
        const sender = body["senderId"];
        const chatRoomId = body["chatRoomId"];

        if (!allMessages.current.has(chatRoomId)) {
            allMessages.current.set(chatRoomId, []);
            // TODO: load from database
        }
        const targetMessages = allMessages.current.get(chatRoomId);
        targetMessages.push([sender, content]);
        console.log(targetMessages);

        if (selectedChatRef.current === chatRoomId) {
            setDisplayedMessages([...targetMessages]);
        }
    }

    const send = (message) => {
        message = message.trim();
        if (message !== "") {
            // alert(currentMessage);
            let newMessages = [...displayedMessages];

            if (newMessages.length >= localMessageLimit) {
                // do nothing for now, replace with queue logic later smh javascript
            }

            // send to server
            const pojo = {
                id: 1234,
                senderId: currentUser.current,
                chatRoomId: selectedChat,
                content: message,
                timestamp: new Date(),
            };
            // test security:
            // stompClient.current.send("/user/" + username + "/queue/messages", {}, JSON.stringify(pojo));
            stompClient.current.send("/app/chat", {}, JSON.stringify(pojo));

            // display for client
            if (!allMessages.current.has(selectedChat)) {
                allMessages.current.set(selectedChat, []);
                // TODO: load from database
            }
            const targetMessages = allMessages.current.get(selectedChat);
            targetMessages.push([currentUser.current, message]);

            setDisplayedMessages([...targetMessages]);
        }
    }

    return (
        <div>
            <OpenedChats currentUser={currentUser.current}
                         openNewChat={(newSelectedChat, newChats) => {
                             setOpenedChats(newChats);
                             setSelectedChat(newSelectedChat);
                         }}
                         openedChats={openedChatsRef.current}
                         selectNewChat={(selected) => {
                             setDisplayedMessages(allMessages.current.has(selected)
                                 ? allMessages.current.get(selected) : []);
                             setSelectedChat(selected);
                         }}
                         selectedChat={selectedChatRef.current}/>
            <MessageBox displayedMessages={displayedMessages}/>
            <ChatBox send={send}/>
        </div>
    );
}

export default Chat;