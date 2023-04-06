import {Chip} from "@mui/material";

type TagProps = {
    tagName: string,
    tagColor: string
}

export default function Tag(props: TagProps) {
    return (
        <Chip variant={"outlined"} label={props.tagName} size={"small"}
              sx={{fontSize: 9, borderColor: props.tagColor, color: props.tagColor}}/>
    );
}


