import './App.css';
import {BrowserRouter, Route, Switch} from "react-router-dom";
import Profile from './profile/Profile';
import LogIn from "./login/LogIn";
import SignUp from "./signup/SignUp";
import Chat from "./chat/Chat";

function App() {
    // const [showText, setShowText] = useState("hello");
    // console.log("called");
    // axios.post('/user/v1/login', {
    //     id: 'yourValue',
    //     password: 'yourOtherValue',
    // }).then(r => {
    //     console.log(r.data);
    //     setShowText(r.data);
    // });
    return (
        <div className="App">
            <BrowserRouter basename={"/ui"}>
                <Switch>
                    <Route exact path="/" render={(props) => <Profile {...props} />}/>
                    <Route
                        exact
                        path="/login"
                        render={(props) => <LogIn {...props} />}
                    />
                    <Route
                        exact
                        path="/signup"
                        render={(props) => <SignUp {...props} />}
                    />
                    <Route exact path="/chat" render={(props) => <Chat {...props} />}/>
                </Switch>
            </BrowserRouter>
        </div>
    );
}

export default App;
