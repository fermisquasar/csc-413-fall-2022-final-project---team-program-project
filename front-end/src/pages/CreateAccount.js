import React from 'react';
import { useNavigate } from 'react-router-dom';


function CreateAccount() {
  const navigate = useNavigate();
  const [userName, setUserName] = React.useState('');
  const [password, setPassword] = React.useState('');
  const [error, setError] = React.useState('');
  const [status, setStatus] = React.useState(false);

  function handleSubmit() {
    const userDto = {
      userName: userName,
      password: password,
    };
    fetch('/createUser', {
      method: 'POST',
      body: JSON.stringify(userDto),
    })
      .then(res => res.json())
      .then(apiRes => {
        if (!apiRes.status) {
          setError(apiRes.message);
        } else {
          navigate('/login');
        }
      })
      .catch(e => {
        console.log(e);
      })
  }

  return (
    <div class="container">
    <div class="screen">
      <h1 class="h1custom"> Create Account </h1>
      <div>
        Username: <div></div>
        <input value={userName} onChange={(e) => setUserName(e.target.value)} />
        <div></div>
        Password: <div></div>
        <input value={password} onChange={(e) => setPassword(e.target.value)} type="password" />
        <div></div>
        <button onClick={handleSubmit}>Create</button>
      </div>
      <div>{error}</div>
    </div>
    </div>
  );
}

export default CreateAccount;