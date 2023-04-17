import {Box} from "@mui/material";
import {MessageType} from "./MessageType";

export default function MessageComponent(props: MessageType) {

    return (
            <Box>
                {props.text} {props.time}
            </Box>
    )
}