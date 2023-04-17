import {Box, TextField} from "@mui/material";
import useWebSocket from "react-use-websocket";
import {useParams} from "react-router-dom";
import Cookies from "js-cookie";
import {ChangeEvent, useState} from "react";
import {MessageType} from "./MessageType";
import MessageComponent from "./MessageComponent";
import SendIcon from '@mui/icons-material/Send';


export default function ChatComponent() {
    const {id} = useParams()
    const userId: string | undefined = Cookies.get("userId") || "unknown"
    const [inputMessage, setInputMessage] = useState("");
    const [receivedInstantMessages, setReceivedInstantMessages] = useState<MessageType[]>([])

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
        if (event.key === 'Enter') {
            event.preventDefault();
            sendMessage(inputMessage);
            setInputMessage('');
        }
    };

    return (
        <Box sx={{
            display: "flex",
            flexDirection: "column",
            flexWrap: "wrap",
            rowGap: "5px",
            width: "100%",
            alignItems: "center",
            alignContent: "center",
            justifyContent: "center",
            justifyItems: "center",
            height: "100%"
        }}>
            <Box sx={{
                display: "flex",
                flexDirection: "column",
                flexWrap: "wrap",
                justifyContent: "bottom",
                justifyItems: "bottom",
                alignItems: "bottom",
                alignContent: "bottom",
                width: "35ch",
                height: "100%"
            }}>
                <Box sx={{
                    flexGrow: 1,
                }}>
                    {receivedInstantMessages.map((message) => (
                        <Box
                            sx={{
                                display: "flex",
                                flexDirection: "column",
                            }}
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
                    sx={{
                        display: "flex",
                        justifyContent: "center",
                        flexDirection: "row",
                        alignItems: "center",
                        position: "fixed",
                        bottom: 0
                    }}>

                    <Box sx={{width: "5px"}}/>

                    <TextField
                        sx={{width: "30ch"}}
                        size="small"
                        placeholder="Enter a message..."
                        id="outlined-size-normal"
                        value={inputMessage}
                        onChange={handleInputChange}
                        onKeyDown={handleKeyDown}
                    />
                    <SendIcon sx={{width: "25px", height: "25px", color: "#18746c"}}/>
                </Box>
                <Box sx={{width: "5px"}}/>
            </Box>
        </Box>
    )
}