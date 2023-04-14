import {Box, TextField} from "@mui/material";
import useWebSocket from "react-use-websocket";
import {useParams} from "react-router-dom";
import Cookies from "js-cookie";
import {ChangeEvent, useState} from "react";
import {MessageType} from "./MessageType";

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
            const userId = messageData.id
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
            rowGap: "20px",
            justifyContent: "center",
            width: "35ch",
            alignItems: "center"
        }}>
            <Box sx={{
                display: "flex",
                flexDirection: "row",
                flexWrap: "wrap",
                rowGap: "5px"
            }}>

                <TextField
                    size="small"
                    placeholder="Enter a message..."
                    id="outlined-size-normal"
                    value={inputMessage}
                    onChange={handleInputChange}
                    onKeyDown={handleKeyDown}
                />
            </Box>
        </Box>
    )
}