
# Universal Plugin

**Universal** is a lightweight and versatile Bukkit/Spigot plugin that adds a collection of essential and fun server commands, enhancing both administrative control and player interaction.

## Features

* Custom join titles and welcome messages
* Broadcast alerts to all players with sound and title
* Server-wide kick command
* Player utilities:

  * `/heal` and `/feed`
  * Show server time
* Gamemode management for self or others:

  * `/gamemode <mode> <player>`
  * Shortcuts: `/gmc`, `/gms`, `/gmsp`, `/gma`
* Simple friendly commands like `/hello`

## Commands

| Command            | Description                                | Permission           |
| ------------------ | ------------------------------------------ | -------------------- |
| `/alert <message>` | Sends a global title alert with sound      | —                    |
| `/kickall`         | Kicks all players except those with bypass | `universal.kickall`  |
| `/hello`           | Says hello back to the player              | —                    |
| `/time`            | Shows in-game world time                   | —                    |
| `/heal`            | Heals the player                           | `universal.heal`     |
| `/feed`            | Refills player's hunger                    | `universal.feed`     |
| `/gamemode`        | Change gamemode of self or others          | `universal.gamemode` |
| `/gmc`             | Shortcut to change to Creative             | `universal.gamemode` |
| `/gms`             | Shortcut to change to Survival             | `universal.gamemode` |
| `/gmsp`            | Shortcut to change to Spectator            | `universal.gamemode` |
| `/gma`             | Shortcut to change to Adventure            | `universal.gamemode` |

## Permissions

* `universal.kickall` — Allows use of `/kickall`
* `universal.kickall.bypass` — Prevents player from being kicked by `/kickall`
* `universal.heal` — Allows use of `/heal`
* `universal.feed` — Allows use of `/feed`
* `universal.gamemode` — Allows use of all gamemode-related commands

## Installation

1. Download the plugin `.jar` file
2. Place it into your server's `plugins/` directory
3. Restart the server
4. Use the commands in-game or from console (where applicable)

## Join Event Behavior

When a player joins the server:

* A title message will welcome them on screen
* A personal welcome message is also sent in chat

## Compatibility

* Tested on Spigot / Paper 1.20+
* Java 21

## License

This plugin is open-source and free to use or modify.


