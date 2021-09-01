import axios from "axios";
import {useState} from "react";

function LogIn(props) {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    const handleUsernameChange = (event) => {
        setUsername(event.target.value);
    }

    const handlePasswordChange = (event) => {
        setPassword(event.target.value);
    }

    const handleSubmit = (event) => {
        event.preventDefault();

        axios.post('/user/v1/login', {
            id: username,
            password: password
        }).then(r => {
            // TODO: change backend response from 404 to something else
            if (r.status === 404) {
                alert("Wrong username or password, try again.")
            } else if (r.status === 200) {
                alert("Welcome back, " + username);

                localStorage.setItem("currentUser", username);
                props.history.push('/chat');
            }
        });
    }

    return (
        <form onSubmit={handleSubmit}>
            <input value={username} placeholder={"username"} onChange={handleUsernameChange}/>
            <input value={password} placeholder={"password"} onChange={handlePasswordChange}/>
            <button type={"submit"}>
                log in
            </button>
        </form>
    );
}

export default LogIn;