# Final Project

Each team member choose 1 front end feature and 1 back end feature to add. An example could be adding a list of all conversations and being able to switch to them, add feature to display usernames on the chat ui, add back end endpoint to reload a user session when user refreshes page. Be creative.

Update this readme with who implemented which feature.

Key Notes:
- If you are testing the application, please try with 2 browsers. One for each user.
- Using one browser will not work because of authentication.

Instructions: 
1. Create 2 users. Example: test1, test2, e.g.
2. Login as test1, add test2 on the message page and send message.
3. Login as test2 and you should be able to see message automically.

B****** P******:
- Added a backend NEW file to handle conversations (createconversation)
- Frontend components for message body
- Frontend handling for messages / conversations,
- autoupdates coversations each time new conversation is added (set interval every 1 sec) and more
- added the image, also change the Live update functionality, both the left side user list and right side messages will update when new data comes
- designed the chat screen as a CSS static design at first and implemented the backend to the work with UI. 

I****** L******:
- Helped implement dao files for AuthDao, ConversationDao, MessageDao, UserDao
- Added CSS styling for navigation bar and buttons (such as hovering effect and different fonts)
- Added CSS styling for create account and login pages (such as gradient screen and transparent user input)
- Added a refreshing feature where it reloads the session every 5 mins, pages do not stay "idle" for long

M****** M******:
- Added and handled logouthandler for the backend
- Added and handled logout.js for the frontend

Elliott Bullard
- Added different themes on the front end. They change the color layouts and stay consistent between pages.
- Added secret phases on the back end. If the phrases are entered into message the message that displays is different then what was sent.
- These messages include: "Cake" or "cake", "I have a secret", "Hello World" or "hello world", "Test" or "test", "What is the meaning of life?", "Who's the best?", and "Hello" or "hello"
