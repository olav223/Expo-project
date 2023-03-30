import {toast} from "react-toastify";

interface notificationProps {
    type: "success" | "error";
    text: string;
}

const notification = (props:notificationProps) => {
    switch (props.type) {
        case "success":
            toast.success(props.text);
            return;
        case "error":
            toast.error(props.text);
            return;
    }
}

export default notification;