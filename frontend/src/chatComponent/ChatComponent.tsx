import {Box, TextField} from "@mui/material";

export default function ChatComponent() {

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
                    label="Last Name"
                    id="outlined-size-normal"
                    value={""}
                    required={true}
                />
            </Box>
        </Box>
    )
}