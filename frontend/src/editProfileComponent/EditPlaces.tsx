import * as React from 'react';
import {ChangeEvent, useState} from 'react';
import Autocomplete from '@mui/material/Autocomplete';
import TextField from '@mui/material/TextField';
import axios from "axios";
import {Place} from "./Place";

export default function EditPlaces() {


    const [inputValue, setInputValue] = useState<string>("");
    const [options, setOptions] = useState<string[]>([]);
    const [place, setPlace] = useState<Place>({name: ""})


    const handleInputChange = async (event: ChangeEvent<HTMLInputElement>) => {
        const query = event.target.value;
        setInputValue(query);
        setPlace({
            name: query
        })

        if (query) {
            const res = await axios.post("/api/places/search", place);
            const cities = res.data;
            setOptions(cities);
        } else {
            setOptions([]);
        }
    };

    return (
        <div>
            <Autocomplete
                multiple
                autoComplete
                id="tags-standard"
                limitTags={5}
                options={options}
                getOptionLabel={(option) => option}
                defaultValue={[]}
                inputValue={inputValue}
                onInputChange={(event, inputValue, reason) => {
                    if (reason === "reset") {
                        setInputValue("");
                    }
                }}
                renderInput={(params) => (
                    <TextField
                        {...params}
                        label="Favorite Places"
                        placeholder="Add up to 5 places"
                        onChange={handleInputChange}
                        helperText="Add up to 5 places"
                        required={true}
                    />
                )}
            />
        </div>
    )
}