import "./OpenedChats.css"
import {useEffect, useState} from "react";
import axios from "axios";

function OpenedChats(props) {
    const [searchNewChatField, setSearchNewChatField] = useState("");
    const [chats, setChats] = useState(["Loading..."]);

    useEffect(() => {
        let keyCount = 0;
        const chatTags = [];
        props.openedChats.forEach(chat => {
            let chatClass = "openedChat";
            if (chat === props.selectedChat) {
                chatClass = "selectedChat";
            }
            chatTags.push(
                <button key={keyCount++} className={chatClass} onClick={() => {
                    props.selectNewChat(chat);
                    // console.log(chat)
                }}>
                    {chat}
                </button>
            );
        });

        setChats(chatTags);
    }, [props]);

    const handleSearchNewChatField = (event) => {
        setSearchNewChatField(event.target.value);
    }

    const handleSearchNewChatSubmit = (event) => {
        event.preventDefault();

        console.log(props.currentUser);
        console.log([props.currentUser, searchNewChatField]);
        console.log(new Date());
        axios.post("/user/v1/addNewChat", {
            userId: props.currentUser,
            users: [props.currentUser, searchNewChatField],
            timestamp: new Date()
        }).then(res => {
            const chatId = res.data;
            props.openNewChat(chatId, [chatId, ...props.openedChats]);

            setSearchNewChatField("");
        }).catch(error => {
            if (error.response) {
                alert(error.response.data);
            } else {
                alert("Unknown error encountered")
            }
        });
    }

    return (
        <div>
            <form onSubmit={handleSearchNewChatSubmit}>
                <input value={searchNewChatField} placeholder={"Start a new chat here..."}
                       onChange={handleSearchNewChatField}/>
                <button type={"submit"}>Add</button>
            </form>
            <div className={"contactList"}>
                {chats}
            </div>
        </div>
    );
}

export default OpenedChats;