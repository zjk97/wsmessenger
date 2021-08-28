import './App.css';

function App() {
    console.log("called");
    let text = 'hello';
    fetch('/user/v1/login', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            id: 'yourValue',
            password: 'yourOtherValue',
        })
    }).then(r => r.body.getReader().read().then(b => console.log(new TextDecoder().decode(b.value))));
    return (
        <div>
            {text}
        </div>
    );
}

export default App;
