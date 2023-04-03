import * as React from "react";
import {ChangeEvent, useEffect, useState} from "react";
import {useNavigate, useParams} from "react-router-dom";
import {UserDetails} from "./UserDetails";
import axios from "axios";

export default function useEditProfile() {
    const [firstNameError, setFirstNameError] = useState<string | boolean>(false)
    const [lastNameError, setLastNameError] = useState<string | boolean>(false)
    const [descriptionError, setDescriptionError] = useState<string | boolean>(false)
    const {userId} = useParams();

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
        return inputFields.firstName.trim().length > 1 &&
            inputFields.lastName.trim().length > 1 &&
            inputFields.description.trim().length > 1 &&
            inputFields.places.length > 1 &&
            inputFields.activities.length > 1
    }

    function validateDescription(description: string): boolean {
        return description.trim().length > 30 && description.trim().length <= 300;
    }

    function updateProfileFormSubmit(evt: React.FormEvent<HTMLFormElement>) {
        evt.preventDefault();

        if (validateInputFields(inputFields) === true) {
            axios.put(`/api/users/profile/${userId}`, {
                picture: "",
                description: inputFields.description,
                firstName: inputFields.firstName,
                lastName: inputFields.lastName,
                places: inputFields.places,
                activities: inputFields.activities
            })
                .then((response) => {
                    navigate(`/edit-profile/${userId}`)
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
        firstNameError,
        lastNameError,
        descriptionError,
        updateProfileFormSubmit
    }
}
