import {Box} from "@mui/material";
import TextField from "@mui/material/TextField";

export default function SearchExperts() {
    return (
        <Box sx={{
            textAlign: "center",
            display: "flex",
            flexDirection: "column",
            flexWrap: "wrap",
            alignContent: "space-around",
            alignItems: "center",
            rowGap: "5px",
            width: "25ch"
        }}>

            <TextField id="outlined-search" label="Search field" type="search"/>

        </Box>

    )
}

