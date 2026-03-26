# Shift Management System - tmux Cheat Sheet

## Project location
```bash
cd /home/vboxuser/.openclaw/workspace/projects/shiftmanagementsystem
```

## Start the app in tmux
```bash
tmux new-session -d -s shiftapp -c /home/vboxuser/.openclaw/workspace/projects/shiftmanagementsystem './mvnw spring-boot:run'
```

## Check running tmux sessions
```bash
tmux ls
```

## Reattach to the running app
```bash
tmux attach -t shiftapp
```

## Detach and leave it running
Press:
```text
Ctrl+B then D
```

## See recent app output without attaching
```bash
tmux capture-pane -t shiftapp -p | tail -40
```

## Stop the app cleanly
Option 1:
```bash
tmux attach -t shiftapp
```
Then press:
```text
Ctrl+C
```

Option 2:
```bash
tmux kill-session -t shiftapp
```

## Open the app
```text
http://localhost:8080
```

## Login
- Username: `admin`
- Password: `Admin123!`

## Helpful note
If `shiftapp` already exists, stop it first before starting a new one:
```bash
tmux kill-session -t shiftapp
```
