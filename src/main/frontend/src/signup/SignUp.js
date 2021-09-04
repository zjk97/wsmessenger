import useState from "react-usestateref";
import axios from "axios";

function SignUp(props) {
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

        axios.post('/user/v1/signup', {
            id: username,
            password: password,
            chatRooms: [],
            openedChats: []
        }).then(r => {
            if (r.status === 400) {
                alert("Username already taken, try another one.")
            } else if (r.status === 200) {
                alert("Made you an account, please log in now.")
                props.history.push('/login')
            }
        });
    }

    return (
        <form onSubmit={handleSubmit}>
            <input value={username} placeholder={"username"} onChange={handleUsernameChange}/>
            <input value={password} placeholder={"password"} onChange={handlePasswordChange}/>
            <button type={"submit"}>
                sign up
            </button>
        </form>
    );
}

export default SignUp;