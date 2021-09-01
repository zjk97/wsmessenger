import "./OpenedChats.css"

function OpenedChats(props) {
    let chats = [];

    const chatArray = props.openedChats;
    let keyCount = 0;
    chatArray.forEach(chat => {
        let chatClass = "openedChat";
        if (chat === props.selectedChat) {
            chatClass = "selectedChat";
        }
        chats.push(
            <button key={keyCount++} className={chatClass} onClick={() => {
                props.selectNewChat(chat);
                // console.log(chat)
            }}>
                {chat}
            </button>
        );
    });

    return (
        <div className={"contactList"}>
            {chats}
        </div>
    );
}

export default OpenedChats;