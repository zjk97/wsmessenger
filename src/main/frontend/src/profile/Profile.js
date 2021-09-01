import {useState} from "react";

function Profile(props) {
    const [loggedInUser, setLoggedInUser] = useState(null);

    const updateLoggedInUser = (newUser) => {
        if (loggedInUser !== newUser) {
            setLoggedInUser(newUser);
        }
    }
    
    return (
        <div>
            {loggedInUser}
            <button onClick={updateLoggedInUser("Nikki Joyce")}>
                log in
            </button>
        </div>
    );
}

export default Profile;