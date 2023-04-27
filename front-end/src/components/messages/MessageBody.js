import React, { useEffect } from 'react'
import Cookies from "universal-cookie";

export default function MessageBody({ props, activeConversationId, activeReceiver }) {

  const [text, setText] = React.useState("");
  const [error, setError] = React.useState("");

  const [messageData, setMessageData] = React.useState([]);

  const getConversations = () => {
    const cookies = new Cookies();
    fetch('/getConversation?conversationId=' + activeConversationId, {
      headers: {
        auth: cookies.get("auth"), // makes the call authorized
      },
    })
      .then((response) => {
        if (response.ok) {
          response.json().then(json => {
            console.log(json)
            setMessageData(json.data)
          });
        }
      })
  }

  
  useEffect(() => {
      getConversations();
      const interval = setInterval(function() {
        getConversations();
      }, 2000);
      return ()=>{clearInterval(interval);};
  }, [activeConversationId])

  const submitMessage = () => {
    const messageDto = {
      fromId: props.loggedInUser,
      toId: activeReceiver,
      message: text,
    };
    const cookies = new Cookies();
    fetch("/createMessage", {
      method: "POST",
      body: JSON.stringify(messageDto),
      headers: {
        auth: cookies.get("auth"), // makes the call authorized
      },
    })
      .then((res) => res.json())
      .then((apiRes) => {
        console.log(apiRes);
        if (apiRes.status) {
          // message was added
          setText("");
          setError("");
          getConversations();
        } else {
          setError(apiRes.message);
        }
      })
      .catch((e) => {
        console.log(e);
      });
  }

  return (
    <div class="app-main">
      <div className="chat-header">
        <li class="chat-list-item">
          <img src='/default_user.png' alt="chat" />
          <span class="chat-list-name">{activeReceiver}</span>
        </li>
      </div>
      <div className="chat-content">
        <div class="chat-wrapper">
          {
            messageData && messageData.map((item, index) => {
              return (
                <>
                  {
                    activeReceiver === item.fromId ? (
                      <div class="message-wrapper">
                        <img
                          class="message-pp"
                          src="/default_user.png"
                          alt="profile-pic"
                        />
                        <div class="message-box-wrapper">
                          <div class="message-box">{item.message}</div>
                          <span>{Date(item.timestamp).toString()}</span>
                        </div>
                      </div>
                    ) : (
                      <div class="message-wrapper reverse">
                        <img
                          class="message-pp"
                          src="/default_user.png"
                          alt="profile-pic"
                        />
                        <div class="message-box-wrapper">
                          <div class="message-box">{item.message}</div>
                          <span>{Date(item.timestamp).toString()}</span>
                        </div>
                      </div>
                    )
                  }
                </>
              )
            })
          }
        </div>
      </div>
      <div className=" chat-footer">
        <div class="chat-input-wrapper">
          <div class="input-wrapper">
            <input
              type="text"
              class="chat-input"
              placeholder="Enter your message here"
              value={text}
              onChange={(e) => { setText(e.target.value) }}
            />
          </div>
          <button class="chat-send-btn" onClick={() => { submitMessage() }}></button>
        </div>
      </div>
    </div>
  )
}
