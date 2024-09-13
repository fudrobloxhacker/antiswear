## Overview

The **AntiSwear** plugin is a comprehensive solution for Minecraft servers to filter and censor inappropriate language in player chat. It provides customizable word lists, regex support, and optional integration with the OpenAI API for advanced profanity detection.

## Features

- **Censorship**: Automatically replaces banned words with a customizable censor character (e.g., `*`).
- **Custom Word Lists**: Easily add or remove banned words using a YAML file or CSV file.
- **Regex Support**: Use regular expressions to detect complex patterns in chat messages.
- **OpenAI Integration**: Optionally utilize OpenAI's API for advanced profanity detection.
- **Logging**: Optionally log instances of profanity attempts for moderation purposes.

## Installation

1. Download the latest release from the [Releases]() page.
2. Place the JAR file in the `plugins` folder of your Minecraft server.
3. Start or restart your server to generate the configuration files.
4. Configure the plugin settings in `config.yml` and `words.yml` as needed.

## Configuration

### config.yml

```yaml
# Character used to censor banned words
censor-character: "*"

# Enable logging of censored messages
enable-logging: true

# Enable regex pattern matching
enable-regex: true

# OpenAI API Key for advanced filtering (leave empty to disable)
openai-api-key: ""

# Message sent to player when their message is censored
censored-message: "&cYour message contained inappropriate language and was censored."
