import {Box} from "@mui/material";
import {MessageType} from "./MessageType";

export default function MessageComponent(props: MessageType) {

    return (
        <Box display={"flex"} flexDirection={"column"}>
            <Box>
                {props.text}
            </Box>
            <Box alignSelf={"flex-end"} fontSize={"10px"}>
                {props.time}
            </Box>
        </Box>
    )
}