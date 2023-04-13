import {Box, TextField} from "@mui/material";
import useWebSocket from "react-use-websocket";
import {useParams} from "react-router-dom";
import Cookies from "js-cookie";
import {ChangeEvent, useState} from "react";

export default function ChatComponent() {
    const {id} = useParams()
    const userId: string | undefined = Cookies.get("userId") || "unknown"
    const [message, setMessage] = useState("");

    const {
        sendMessage
    } = useWebSocket(`ws://localhost:8080/api/ws/chat/${id}?userId=${userId}`, {
        onOpen: () => {
        },
        shouldReconnect: () => true,
    });

    const handleInputChange = (event: ChangeEvent<HTMLInputElement>) => {
        setMessage(event.target.value);
    };
    const handleKeyDown = (event: React.KeyboardEvent<HTMLInputElement>) => {
        if (event.key === 'Enter') {
            event.preventDefault();
            sendMessage(message);
            setMessage('');
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
                    value={message}
                    onChange={handleInputChange}
                    onKeyDown={handleKeyDown}
                />
            </Box>
        </Box>
    )
}