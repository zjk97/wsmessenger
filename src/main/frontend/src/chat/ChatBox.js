import {useState} from "react";

function ChatBox(props) {
    const [message, setMessage] = useState("");

    const handleSend = (event) => {
        props.send(message);
        setMessage("");

        event.preventDefault();
    }

    const handleMessageChange = (event) => {
        setMessage(event.target.value);
    }

    return (
        <form onSubmit={handleSend}>
            <input value={message} placeholder={"what's on your mind..."} onChange={handleMessageChange}/>
            <button type={"submit"}>
                Send
            </button>
        </form>
    );
}

export default ChatBox;