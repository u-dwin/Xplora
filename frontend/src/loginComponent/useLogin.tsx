import {ChangeEvent, useEffect, useState} from "react";
import {User} from "./User";
import {useNavigate} from "react-router-dom";
import axios from "axios"

export default function useLogin() {
    const [emailError, setEmailError] = useState<string | boolean>(false)
    const [passwordError, setPasswordError] = useState<string | boolean>(false)
    const [loginUser, setLoginUser] = useState<User>()

    const navigate = useNavigate();

    const [inputFields, setInputFields] = useState({
        email: "",
        password: "",
    })

    function handleEmailChange(evt: ChangeEvent<HTMLInputElement>) {
        setInputFields({...inputFields, email: evt.target.value})
    }

    function handlePasswordChange(evt: ChangeEvent<HTMLInputElement>) {
        setInputFields({...inputFields, password: evt.target.value})
    }

    useEffect(() => {
        setLoginUser({
            userEmailAddress: inputFields.email,
            userPassword: inputFields.password
        })
    }, [inputFields, passwordError, emailError])

    useEffect(() => {
        setEmailError(false)
        setPasswordError(false)
    }, [inputFields])

    function loginFormSubmit(evt: React.FormEvent<HTMLFormElement>) {
        evt.preventDefault();

        axios.post("/api/users/login", {
                userEmailAddress: loginUser?.userEmailAddress,
                userPassword: loginUser?.userPassword
            })
                .then((response) => {
                    const id = response.data.userId
                    navigate(`/edit-profile/${id}`)
                })
                .catch((error) => console.error(error))
    }

    return {
        loginFormSubmit,
        handleEmailChange,
        handlePasswordChange,
        inputFields
    }
}
