import React, { useEffect, useState } from "react";
import Cookies from "universal-cookie";
import MessageBody from "../components/messages/MessageBody";
import "./message.css";

function Messages(props) {

  const [value, setValue] = useState('');
  const [allConversations, setAllConversations] = useState([]);


  const getConversations = () => {
    const cookies = new Cookies();
    fetch('/getConversations?userName=' + props.loggedInUser, {
      headers: {
        auth: cookies.get("auth"), // makes the call authorized
      },
    })
      .then((response) => {
        if (response.ok) {
          response.json().then(json => {
            setAllConversations(json.data)
          });
        }
      })
  }

  useEffect(() => {      
    getConversations()
    const interval = setInterval(function() {
      getConversations();
    }, 5000);
    return ()=>{clearInterval(interval);};
  }, [])

  const addConversation = () => {
    const messageDto = {
      fromId: props.loggedInUser,
      toId: value,
    };
    const cookies = new Cookies();
    fetch("/createConversation", {
      method: "POST",
      body: JSON.stringify(messageDto),
      headers: {
        auth: cookies.get("auth"), // makes the call authorized
      },
    })
      .then((res) => res.json())
      .then((apiRes) => {
        if (apiRes.status) {
          getConversations();
        } else {

        }
      })
      .catch((e) => {
        console.log(e);
      });
  }

  const [activeConversation, setActiveConversation] = useState(null);
  const [activeReceiver, setActiveReceiver] = useState('');

  return (
    <div class="app-container">
      <div class="app-left">
        <div className="add-user-container">
          <input type="text" value={value} onChange={(e) => { setValue(e.target.value) }} placeholder="Start a new chat" className="input" />
          <button className="add-user" onClick={() => { addConversation() }}>ADD</button>
        </div>
        <div class="chat-list-wrapper">
          <ul class="chat-list active">
            {
              allConversations && allConversations.map((item, index) => {
                return (
                  <li
                    className={`chat-list-item ${activeConversation === item.conversationId && "active"}`}
                    key={index}
                    onClick={() => {
                      setActiveConversation(item.conversationId);
                      setActiveReceiver(props.loggedInUser === item.receiverId ? item.senderId : item.receiverId)
                    }}
                  >
                    <img src="/default_user.png" alt="chat" />
                    <span class="chat-list-name">{props.loggedInUser === item.receiverId ? item.senderId : item.receiverId}</span>
                  </li>
                )
              })
            }
          </ul>
        </div>
      </div>
      {
        activeConversation && <MessageBody props={props} activeConversationId={activeConversation} activeReceiver={activeReceiver} />
      }
    </div>
  );
}

export default Messages;
