# ⛏️ Modern Chat
### <center>[**\[ Join Discord \]**](https://discord.gg/xHubQfKJPv)&emsp;[**\[ Report Issue \]**](https://github.com/ch1ck3n-ovo/GenshinThirdPerson/issues)</center>
A **Minecraft Paper plugin** that enhances server communication by introducing a **lightweight, native Adventure-based chat engine**. This plugin allows for a smoother, more immersive and interactive chat experience.

> ⚠️ **IMPORTANT NOTICE: Microsoft Chat Reporting**  
> Please be aware that using this plugin overrides the vanilla chat mechanics. As a result, the **official Microsoft Player Chat Reporting system will be completely disabled** on your server.
>
> *By downloading and installing this plugin, you acknowledge and agree to opt out of Microsoft's native chat reporting features.*

# 🎥 Demonstration
## Chat Format
### Color
Usage: `<red>R <green>G <blue>B`

![Color](https://github.com/ch1ck3n-ovo/ModernChat/blob/master/assets/demonstration-color.png?raw=true)

### Shadow
Usage: `<shadow:white><black>BLACK`

![Color](https://github.com/ch1ck3n-ovo/ModernChat/blob/master/assets/demonstration-shadow.png?raw=true)

### Decoration
Usage: `<b>a</b> <i>b</i> <u>c</u> <st>d</st> <obf>e</obf>`

![Color](https://github.com/ch1ck3n-ovo/ModernChat/blob/master/assets/demonstration-decoration.png?raw=true)

### Gradient
Usage: `<gradient>||||||||||||||||||||||||</gradient>`

![Color](https://github.com/ch1ck3n-ovo/ModernChat/blob/master/assets/demonstration-gradient.png?raw=true)

### Rainbow
Usage: `<rainbow>||||||||||||||||||||||||</rainbow>`

![Color](https://github.com/ch1ck3n-ovo/ModernChat/blob/master/assets/demonstration-rainbow.png?raw=true)

## Chat Channel
### Local
Usage: `hello`

![Color](https://github.com/ch1ck3n-ovo/ModernChat/blob/master/assets/demonstration-local.png?raw=true)

### Global
Usage: `#all hello starts with #all`

![Color](https://github.com/ch1ck3n-ovo/ModernChat/blob/master/assets/demonstration-global.png?raw=true)

## Other
### Item
Usage: `[item]`

![Color](https://github.com/ch1ck3n-ovo/ModernChat/blob/master/assets/demonstration-item.png?raw=true)

### Mention
Usage: `@ch1ck3n_ovo`

![Color](https://github.com/ch1ck3n-ovo/ModernChat/blob/master/assets/demonstration-mention.png?raw=true)

### Rate Limiter
![Color](https://github.com/ch1ck3n-ovo/ModernChat/blob/master/assets/demonstration-rate.png?raw=true)

### Clickable Name
![Color](https://github.com/ch1ck3n-ovo/ModernChat/blob/master/assets/demonstration-clickable.png?raw=true)



# 🌟 Features
- **MiniMessage Formatting**: Fully supports modern RGB gradients, color tags, and custom Global/Local channel prefixes.
- **Smart Chat Filter**: Automatically detects and replaces inappropriate words from a customizable blacklist with censor tags (e.g. `*`).
- **Radius-Based Local Chat**: Keeps nearby conversation immersive by isolating local chat within a configurable block distance.
- **Interactive Item Showcase**: Easily display held items in chat by typing `[item]`, complete with full hover details and enchantments.
- **Player Mention Notifications**: Highlight player names and trigger an audible alert ping when mentioned with `@<player>`.
- **Anti-Spam Rate Limiter**: Prevent chat spamming with a configurable cooldown timer and automated warning messages.
- **Clickable Usernames**: Interacting made easy—hover over a player's name to view custom text and click to quickly suggest a message command.

# ⚙️ Configuration
<details>  
<summary>Click to expand.</summary>

```
# +------------------------------------------------------+ #
# |                   Chat Formatting                    | #
# +------------------------------------------------------+ #
chat-format:
  global-prefix: "<dark_gray>[</dark_gray><gradient:#00c6ff:#0072ff>Global</gradient><dark_gray>] </dark_gray>"
  local-prefix: "<dark_gray>[</dark_gray><gradient:#00c6ff:#0072ff>Local</gradient><dark_gray>] </dark_gray>"
  separate-char: " <dark_gray>»</dark_gray> "

# +------------------------------------------------------+ #
# |                     Chat Filter                      | #
# +------------------------------------------------------+ #
chat-filter:
  enable: true
  replacement-tag: "*"
  patterns:
    - "fxxk"
    - "sxxt"
    - "bxxch"

# +------------------------------------------------------+ #
# |                    Chat Radius                       | #
# +------------------------------------------------------+ #
chat-radius:
  enable: true
  distance: 100

# +------------------------------------------------------+ #
# |                    Chat Item                         | #
# +------------------------------------------------------+ #
chat-item:
  enable: true

# +------------------------------------------------------+ #
# |                    Chat Mention                      | #
# +------------------------------------------------------+ #
chat-mention:
  enable: true

# +------------------------------------------------------+ #
# |                   Chat Cooldown                      | #
# +------------------------------------------------------+ #
chat-cooldown:
  enable: true
  time-ms: 1000
  message: "<red>You speak too fast.</red>"

# +------------------------------------------------------+ #
# |                 Clickable Usernames                  | #
# +------------------------------------------------------+ #
clickable-name:
  enable: true
  hover-text: "<yellow>Click to message %player%</yellow>"
  suggest-command: "/msg %player% "
```
</details>  

# 📥 Installation
1. **Server Requirement**: Ensure your server is running PaperMC (requires version 1.21+).
2. **Get the Latest Release**: Download the plugin .jar file from [Modrinth](https://modrinth.com/mod/modernchat).
3. **Move the Plugin to `plugins` Folder**: Place the `.jar` file inside your `plugins` folder.
4. **Restart the Server**: Start or restart your server to generate the configuration files and enjoy a lag-free chat experience!

# 💖 Credits
Developed by **ch1ck3n-ovo**.

# 📜 License
This mod is licensed under [**GPL-3.0 License**](https://github.com/ch1ck3n-ovo/ModernChat/blob/main/LICENSE).

---
*Enhance your server today with smart chat filtering and interactive item showcases!*