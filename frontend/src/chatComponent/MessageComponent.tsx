import {Box} from "@mui/material";
import {MessageType} from "./MessageType";

export default function MessageComponent(props: MessageType) {

    return (
        <Box sx={{
            display: "flex",
            flexDirection: "column",
            flexWrap: "wrap",
            rowGap: "20px",
            justifyContent: "center",
            width: "25ch",
            alignItems: "center"
        }}>
            <Box>
                {props.text}" " {props.time}
            </Box>
        </Box>
    )
}