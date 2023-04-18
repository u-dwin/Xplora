import {Box, TextField} from "@mui/material";
import useWebSocket from "react-use-websocket";
import {useLocation, useParams} from "react-router-dom";
import Cookies from "js-cookie";
import {ChangeEvent, useEffect, useRef, useState} from "react";
import {MessageType} from "./MessageType";
import MessageComponent from "./MessageComponent";
import SendIcon from '@mui/icons-material/Send';
import Avatar from "@mui/material/Avatar";


export default function ChatComponent() {
    const {id} = useParams()
    const location = useLocation();
    const chatPartner = location.state.user;
    const userId: string | undefined = Cookies.get("userId") || "unknown"
    const [inputMessage, setInputMessage] = useState("");
    const [receivedInstantMessages, setReceivedInstantMessages] = useState<MessageType[]>([])
    const messagesContainerRef = useRef<HTMLElement>(null);

    useEffect(() => {
        const messagesContainer = messagesContainerRef.current;
        if (messagesContainer) {
            messagesContainer.scrollTop = messagesContainer.scrollHeight;
        }
    }, [receivedInstantMessages])


    const {
        sendMessage
    } = useWebSocket(`ws://localhost:8080/api/ws/chat/${id}?userId=${userId}`, {
        onOpen: () => {
        },
        shouldReconnect: () => true,
        onMessage: (event: MessageEvent) => {
            const messageData = JSON.parse(event.data)
            const time = messageData.time.substring(11, 16)
            const text = messageData.text
            const userId = messageData.userId
            const newMessage: MessageType = {time, text, userId};
            setReceivedInstantMessages((prevMessages) => [...prevMessages, newMessage])
        }
    });

    const handleInputChange = (event: ChangeEvent<HTMLInputElement>) => {
        setInputMessage(event.target.value);
    };
    const handleKeyDown = (event: React.KeyboardEvent<HTMLInputElement>) => {
        if (event.key === 'Enter' && inputMessage.length > 0) {
            event.preventDefault();
            sendMessage(inputMessage);
            setInputMessage('');
        }
    };

    const handleSendButtonClick = (event: React.MouseEvent<HTMLInputElement>) => {
        if (inputMessage.length > 0) {
            event.preventDefault();
            sendMessage(inputMessage);
            setInputMessage('');
        }
    };

    return (
        <Box
            sx={{
                display: "flex",
                width: "100%",
                flexDirection: "column",
                alignItems: "center"
            }}>
            <Box
                sx={{
                    display: "flex",
                    flexDirection: "column",
                    height: "42rem",
                    width: "40ch",
                }}
            >
                <Box
                    display="flex"
                    alignItems="center"
                    justifyContent="center"
                    padding="0.6rem"
                    columnGap="0.6rem"
                >
                    <Avatar
                        alt="profile"
                        src={chatPartner.picture}/>
                    <Box>{chatPartner.firstName} {chatPartner.lastName}</Box>
                </Box>

                <Box
                    ref={messagesContainerRef}
                    flex={1}
                    overflow="auto"
                    padding="1rem"
                >
                    {receivedInstantMessages.map((message) => (
                        <Box
                            display="flex"
                            flexDirection="column"
                            key={`${message.text}-${message.time}-${message.userId}`}
                        >
                            {message.userId === userId ? (
                                <Box
                                    sx={{
                                        alignSelf: "flex-end",
                                        padding: "5px",
                                        margin: "5px",
                                        borderStyle: "solid",
                                        borderColor: "#18746c",
                                        borderRadius: "10px"
                                    }}
                                >
                                    <MessageComponent
                                        text={message.text}
                                        userId={message.userId}
                                        time={message.time}
                                    />
                                </Box>
                            ) : (
                                <Box
                                    sx={{
                                        alignSelf: "flex-start",
                                        padding: "5px",
                                        margin: "5px",
                                        borderStyle: "solid",
                                        borderColor: "#31b585",
                                        borderRadius: "10px"
                                    }}
                                >
                                    <MessageComponent
                                        text={message.text}
                                        userId={message.userId}
                                        time={message.time}
                                    />
                                </Box>
                            )}
                        </Box>
                    ))}
                </Box>

                <Box
                    display="flex"
                    flexDirection="row"
                    justifyItems="center"
                    alignItems="center"
                    columnGap="1rem"
                    p={1}
                    sx={{
                        overflow: "hidden"
                    }}
                >

                    <TextField
                        sx={{width: "30ch"}}
                        size="small"
                        placeholder="Enter a message..."
                        id="outlined-size-normal"
                        value={inputMessage}
                        onChange={handleInputChange}
                        onKeyDown={handleKeyDown}
                    />
                    <Box onClick={handleSendButtonClick}>
                        <SendIcon sx={{width: "25px", height: "25px", color: "#18746c"}}/>
                    </Box>
                </Box>
            </Box>
        </Box>
    )
}