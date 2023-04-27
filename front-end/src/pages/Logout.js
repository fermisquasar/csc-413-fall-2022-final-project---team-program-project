import React from 'react';
import { useNavigate } from 'react-router-dom';
import Cookies from "universal-cookie";

function Logout(props) {
  const navigate = useNavigate();
  const [error, setError] = React.useState('');

  function handleSubmit() {
const cookies = new Cookies();
    fetch('/logout', {
      method: 'POST',
      headers: {
        auth: cookies.get("auth"), // makes the call authorized
      },
    })
      //.then(res => res.json())
      .then(apiRes => {
        if(apiRes.status === 200){
          // todo login logic
          props.setIsLoggedIn(false);
          props.setLoggedInUser('');
          navigate('/login');
        }
      })
      .catch(e => {
        console.log(e);
      })
  }
  return (
    <div class="logoutcontainer">
    <div class="logoutscreen">
      <h1 class="h1custom"> Are you sure? </h1>
      <div>
        <button onClick={handleSubmit}>Logout</button>
      </div>
      <div>{error}</div>
    </div>
    </div>
  );
}
export default Logout;