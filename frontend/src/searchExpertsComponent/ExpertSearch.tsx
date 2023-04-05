import {Box} from "@mui/material";
import TextField from "@mui/material/TextField";
import SearchIcon from '@mui/icons-material/Search';

export default function ExpertSearch() {
    return (
        <Box sx={{
            display: "flex",
            flexDirection: "column",
            flexWrap: "wrap",
            rowGap: "5px",
            justifyContent: "center",
            width: "100%"
        }}>

            <Box sx={{height: "20px"}}></Box>

            <Box
                sx={{
                    display: "flex",
                    justifyContent: "center",
                    flexDirection: "row",
                    columnGap: "5px",
                    justifyItems: "center",
                    alignItems: "center",
                    width: "100%"
                }}
            >
                <SearchIcon fontSize="large"/>
                <TextField id="outlined-search" label="" type="search"/>
            </Box>
        </Box>

    )
}

