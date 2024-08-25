# Chat Application

**Status:** Discontinued

## Overview

This project is a chat application with basic functionality. It includes features for user authentication and direct messaging.

## Features

- User registration and login
- Sending direct messages
- Retrieving chat history

## Endpoints

### Authentication

- **POST** `/api/auth/login`
    - Logs in a user with credentials.

- **POST** `/api/auth/register`
    - Registers a new user.

### Chat

- **POST** `/api/chat.sendDirectMessage`
    - Sends a direct message.
    - **Response**: The sent message is broadcast to `/queue/chat/{chatId}`.

- **GET** `/api/chat/{chatId}`
    - Retrieves chat history for a specific chat.
    - **Query Parameters**:
        - `page` (int) - Optional, default is 0
        - `size` (int) - Optional, default is 150

## Code Overview

- **AuthController**: Manages user authentication
    - `login()`
    - `register()`

- **ChatController**: Manages chat functionality
    - `sendDirectMessage()`
    - `getChatHistory()`

## Note

This project is not supported yet, but I will continue to do it in the future
