import React from 'react';

function Home() {
  return (
    <div>
      <h1> Home </h1>

        <aside class="theme-wrapper">
          <div class="theme-container" role="group">
            <button class="btn theme" aria-label="Apply light theme" data-theme="light">Light Theme</button>
            <button class="btn theme" aria-label="Apply dark theme" data-theme="dark">Dark Theme</button>
            <button class="btn theme" aria-label="Apply custom theme" data-theme="custom">Custom Theme</button>
          </div>
        </aside>

    </div>
  );
}

//CUT AND PASTE THIS ON BREAK

const themeBtns = document.querySelectorAll('.theme');

const handleThemeChange = (e) => {
    const theme = e.currentTarget.dataset.theme;
    document.documentElement.setAttribute('data-theme', theme);
    localStorage.setItem('myCustomTheme', theme)
}

themeBtns.forEach(t => t.addEventListener('click', handleThemeChange))

window.addEventListener('DOMContentLoaded', () =>{
    const theme = localStorage.getItem('myCustomTheme');
    theme && document.documentElement.setAttribute('data-theme', theme);
})

export default Home;