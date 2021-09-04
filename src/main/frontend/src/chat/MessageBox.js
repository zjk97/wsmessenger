import React, {useEffect, useRef} from "react";

function MessageBox(props) {
    const messagesEndRef = useRef(null);
    useEffect(() => {
        messagesEndRef.current.scrollIntoView({behavior: 'smooth'});
    });

    const messages = [];
    let keyCount = 0;
    const currentUser = localStorage.getItem("currentUser");

    props.displayedMessages.forEach(message => {
        const sender = message[0];
        const content = message[1];
        const userClass = sender === currentUser ? "currentUser" : "partnerUser";

        messages.push(
            <div key={keyCount}>
                <div className={userClass}>
                    {sender}:
                </div>
                <div>
                    {content}
                </div>
            </div>
        );

        ++keyCount;
    });

    return (
        <div className={"messageBox"}>
            {messages}
            <div ref={messagesEndRef}/>
        </div>
    );
}

export default MessageBox;