import React, {useEffect, useRef, useState} from "react";
import "./Chat.css"
import ChatBox from "./ChatBox";
import OpenedChats from "./OpenedChats";
import axios from "axios";

function Chat(props) {
    const messagesEndRef = useRef(null);
    const [messages, setMessages] = useState([]);
    const [selectedChat, setSelectedChat] = useState("");
    const [openedChats, setOpenedChats] = useState(["Loading..."]);

    let keyCount = useRef(0);
    let stompClient = useRef(null);
    let currentUser = useRef(localStorage.getItem("currentUser"));
    const localMessageLimit = 30;

    useEffect(() => {
        messagesEndRef.current.scrollIntoView({behavior: 'smooth'});
    });

    // load opened chats only once, provide empty dependency array
    useEffect(() => {
        axios.post('/user/v1/openedChats?userId=' + currentUser.current).then(r => {
            setOpenedChats(r.data);
        });
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

    useEffect(() => {
        if (localStorage.getItem("currentUser") == null) {
            props.history.push("/login");
        } else {
            connect();
        }
    }, [props.history, connect]);

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
        const partner = body["senderId"];
        let newMessages = [...messages];

        newMessages.push((
            <div key={keyCount.current}>
                <div className={"partnerUser"}>
                    {partner}:
                </div>
                <div>
                    {content}
                </div>
            </div>
        ));
        keyCount.current += 1;

        setMessages(newMessages);
    }

    const send = (message) => {
        message = message.trim();
        if (message !== "") {
            // alert(currentMessage);
            let newMessages = [...messages];

            if (newMessages.length >= localMessageLimit) {
                // do nothing for now, replace with queue logic later smh javascript
            }

            newMessages.push((
                <div key={keyCount.current}>
                    <div className={"currentUser"}>
                        {localStorage.getItem("currentUser")}:
                    </div>
                    <div>
                        {message}
                    </div>
                </div>
            ));
            keyCount.current += 1;

            setMessages(newMessages);

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
        }
    }

    return (
        <div>
            <OpenedChats currentUser={currentUser.current}
                         openNewChat={(newSelectedChat, newChats) => {
                             setOpenedChats(newChats);
                             setSelectedChat(newSelectedChat);
                         }}
                         openedChats={openedChats}
                         selectNewChat={(selected) => setSelectedChat(selected)}
                         selectedChat={selectedChat}/>
            <div className={"messageBox"}>
                {messages}
                <div ref={messagesEndRef}/>
            </div>
            <ChatBox send={send}/>
        </div>
    );
}

export default Chat;