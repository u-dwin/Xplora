import * as React from "react";
import {ChangeEvent, useEffect, useRef, useState} from "react";
import {useNavigate, useParams} from "react-router-dom";
import {UserDetails} from "./UserDetails";
import axios from "axios";

export default function useEditProfile() {
    const [notAllFieldsFilledError, setNotAllFieldsFilledError] = useState<string | boolean>(false)
    const {userId} = useParams();
    const fileInputRef = useRef<HTMLInputElement>(null);
    let profile_picture_url: string = ""

    const navigate = useNavigate();
    const [inputFields, setInputFields] = useState<UserDetails>({
        picture: "",
        description: "",
        firstName: "",
        lastName: "",
        places: [],
        activities: [],
    })

    useEffect(() => {
        axios.get(`/api/users/profile/${userId}`)
            .then((response) => {
                setInputFields(response.data);
            })
            .catch((error) => {
                console.log(error);
            });
    }, [userId]);

    useEffect(() => {
        setNotAllFieldsFilledError(false)
    }, [inputFields])

    function handleFirstNameChange(evt: ChangeEvent<HTMLInputElement>) {
        setInputFields({...inputFields, firstName: evt.target.value})
    }

    function handleLastNameChange(evt: ChangeEvent<HTMLInputElement>) {
        setInputFields({...inputFields, lastName: evt.target.value})
    }

    function handleDescriptionChange(evt: ChangeEvent<HTMLInputElement>) {
        setInputFields({...inputFields, description: evt.target.value})
    }

    const handleActivitySelectionChange = (evt: React.SyntheticEvent, value: string[]) => {
        if (inputFields.activities.length <= 6) {
            setInputFields({...inputFields, activities: value});
        }
    }

    const handlePlacesSelectionChange = (evt: React.SyntheticEvent, value: string[]) => {
        if (inputFields.places.length <= 4) {
            setInputFields({...inputFields, places: value});
        }
    }


    function validateInputFields(inputFields: UserDetails): boolean {
        return inputFields.firstName.trim().length > 0 &&
            inputFields.lastName.trim().length > 0 &&
            inputFields.description.trim().length > 0 &&
            inputFields.places.length > 0 &&
            inputFields.activities.length > 0
    }

    function handleFileUpload() {
        fileInputRef.current?.click();
    }

    function handleFileSelected(event: React.ChangeEvent<HTMLInputElement>) {
        const file = event.target.files?.[0];
        if (file) {
            axios.post("api/photos/add", file)
                .then((response) => {
                    profile_picture_url = response.data
                    setInputFields({...inputFields, picture: profile_picture_url})
                });
        }
    }

    function updateProfileFormSubmit(evt: React.FormEvent<HTMLFormElement>) {
        evt.preventDefault();

        if (!validateInputFields(inputFields)) {
            setNotAllFieldsFilledError("Please fill in all required fields")
        } else {
            axios.put(`/api/users/profile/${userId}`, {
                picture: "",
                description: inputFields.description,
                firstName: inputFields.firstName,
                lastName: inputFields.lastName,
                places: inputFields.places,
                activities: inputFields.activities
            })
                .then(() => {
                    navigate(`/update-profile-success`)
                })
        }
    }

    return {
        handleLastNameChange,
        handleFirstNameChange,
        inputFields,
        setInputFields,
        handleDescriptionChange,
        handleActivitySelectionChange,
        handlePlacesSelectionChange,
        notAllFieldsFilledError,
        updateProfileFormSubmit,
        handleFileSelected,
        handleFileUpload,
        fileInputRef
    }
}
