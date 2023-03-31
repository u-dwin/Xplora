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
        places: [""],
        activities: [""],
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

    function validateFirstName(firstName: string) {
        return firstName.trim().length > 1;
    }

    function validateLastName(lastName: string): boolean {
        return lastName.trim().length > 1;
    }

    function validateDescription(description: string): boolean {
        return description.trim().length > 30 && description.trim().length <= 300;
    }

    /*function userUpdateFormSubmit(evt: React.FormEvent<HTMLFormElement>) {
        evt.preventDefault();

        if (vali(inputFields.email) && validatePassword(inputFields.password)) {
            axios.post("/api/users/add", {
                userType: addUser?.userType,
                userEmailAddress: addUser?.userEmailAddress,
                userPassword: addUser?.userPassword
            })
                .then(() => {
                    navigate("/sign-up-success")
                })
                .catch((error) => console.error(error))
        } else if (!validateEmail(inputFields.email)) {
            return setEmailError("Please enter a valid email address")
        } else if (!validatePassword(inputFields.password)) {
            return setPasswordError("Please enter a password with at least 6 characters")
        }
    }*/

    return {
        handleLastNameChange,
        handleFirstNameChange,
        inputFields,
        handleDescriptionChange,
        firstNameError,
        lastNameError,
        descriptionError
    }
}
