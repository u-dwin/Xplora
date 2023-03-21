import {useState} from "react";
import {User} from "./User";
import {useNavigate} from "react-router-dom";

export default function useAddUser() {
    const [addUser, setAddUser] = useState<User | null>(null)
    const navigate = useNavigate();


    function travelerChoiceClick() {
        setAddUser({
                role: "traveler",
                email: "",
                password: "",
            }
        )
        navigate("/sign-up")
    }

    function expertChoiceClick() {
        setAddUser({
                role: "expert",
                email: "",
                password: "",
                }
            )
        navigate("/sign-up")
    }

    function registrationFormSubmit() {
        setAddUser({
            role: addUser?.role ?? "",
            email: "",
            password: "",
        })
    }

    return {travelerChoiceClick, expertChoiceClick}
}
