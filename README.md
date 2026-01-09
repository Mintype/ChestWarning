# Chest Warning Mod

A lightweight **Fabric 1.21.11** mod designed for server administrators to prevent accidental griefing. It ensures every player receives a clear, one-time warning about stealing and server rules the first time they attempt to open a chest.

## Features

* **First-Interaction Warning:** Intercepts the first time a player right-clicks a chest or trapped chest.
* **Action Interruption:** Automatically cancels the first chest-opening action so the player is forced to acknowledge the warning.
* **Persistent Storage:** Uses the **Fabric Data Attachment API** to ensure that once a player is warned, the status is saved permanently to their data file—even across server restarts or player deaths.

## Installation

1.  **Requirement:** Minecraft 1.21.11 and the [Fabric Loader](https://fabricmc.net/).
2.  **Dependencies:** Ensure [Fabric API](https://modrinth.com/mod/fabric-api) is installed in your `/mods` folder.
3.  **Setup:** Drop the `ChestWarningMod.jar` into your server or client `/mods` folder.

## Contributing

Contributions are welcome! Feel free to open an issue or submit a pull request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
