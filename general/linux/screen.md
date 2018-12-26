# screen in bash
## man
```
screen
Multiplex a physical terminal between several processes (typically interactive shells).

Syntax:
 
   Start a screen session:

      screen [ -options ] [ cmd [args] ]

   Resume a detached screen session:

      screen -r [[pid.]tty[.host]]

      screen -r sessionowner/[[pid.]tty[.host]]

Options:

   -A -[r|R]     Adapt all windows to the new display width & height.
   -c file       Read configuration file instead of .screenrc
   -d (-r)       Detach the elsewhere running screen (and reattach here).
   -dmS name     Start as daemon: Screen session in detached mode.
   -D (-r)       Detach and logout remote (and reattach here).
   -D -RR        Do whatever is needed to Reattach a screen session.
   -d -m         Start in "detached" mode. Useful for system startup scripts.
   -D -m         Start in "detached" mode, & don't fork a new process.
   -list         List our SockDir and do nothing else (-ls) 
   -r            Reattach to a detached screen process.
   -R            Reattach if possible, otherwise start a new session.
   -t title      Set title. (window's name).
   -U            Tell screen to use UTF-8 encoding.
   -x            Attach to a not detached screen. (Multi display mode).
   -X            Execute cmd as a screen command in the specified session.
	
Interactive commands (default key bindings):

     Control-a ?    Display brief help
     Control-a "    List all windows for selection
     Control-a '    Prompt for a window name or number to switch to.
     Control-a 0    Select window 0
     Control-a 1    Select window 1
     ...            ...
     Control-a 9    Select window 9
     Control-a A    Accept a title name for the current window.
     Control-a b    Send a break to window
     Control-a c    Create new window running a shell
     Control-a C    Clear the screen
     Control-a d    Detach screen from this terminal.
     Control-a D D  Detach and logout.
     Control-a f    Toggle flow on, off or auto.
     Control-a F    Resize the window to the current region size.
     Control-a h    Write a hardcopy of the current window to file "hardcopy.n"
     Control-a H    Begin/end logging of the current window to file "screenlog.n"
     Control-a i    Show info about this window.
     Control-a k    Kill (Destroy) the current window.
     Control-a l    Fully refresh current window
     Control-a M    Monitor the current window for activity {toggle on/off}
     Control-a n    Switch to the Next window
     Control-a N    Show the Number and Title of window
     Control-a p    Switch to the Previous window
     Control-a q    Send a control-q to the current window(xon)
     Control-a Q    Delete all regions but the current one.(only)
     Control-a r    Toggle the current window's line-wrap setting(wrap)
     Control-a s    Send a control-s to the current window(xoff)
     Control-a w    Show a list of windows (windows)
     Control-a x    Lock this terminal (lockscreen)
     Control-a X    Kill the current region(remove)
     Control-a Z    Reset the virtual terminal to its "power-on" values
     Control-a Control-\    Kill all windows and terminate screen(quit)
     Control-a :    Enter command line mode(colon)
     Control-a [    Enter copy/scrollback mode(copy)
     Control-a ]    Write the contents of the paste buffer to stdin(paste)
     Control-a _    Monitor the current window for inactivity {toggle on/off}
     Control-a *    Show  a listing of all currently attached displays.
When screen is called, it creates a single window with a shell in it (or the specified command) and then gets out of your way so that you can use the program as you normally would.

Then, at any time, you can:

Create new (full-screen) windows with other programs in them (including more shells)

Kill existing windows

View a list of windows

Switch between windows - all windows run their programs completely independent of each other. Programs continue to run when their window is currently not visible and even when the whole screen session is detached from the user's terminal.

The interactive commands above assume the default key bindings. You can modify screen’s settings by creating a ~/.screenrc file in your home directory. This can change the default keystrokes, bind function keys F11, F12 or even set a load of programs/windows to run as soon as you start screen.

To start screen automatically at login, set your .profile file to read:

exec screen

Attaching and Detaching
Once you have screen running, switch to any of the running windows and type Control-a d. this will detach screen from this terminal. Now, go to a different machine, open a shell, ssh to the machine running screen (the one you just detached from), and type: % screen -r

This will reattach to the session. Just like magic, your session is back up and running, just like you never left it.

Exiting screen completely
Screen will exit automatically when all of its windows have been killed.

Close whatever program is running or type `Exit ' to exit the shell, and the window that contained it will be killed by screen. (If this window was in the foreground, the display will switch to the previous window)

When none are left, screen exits.

This page is just a summary of the options available, type man screen for more.
```

