import {ReactNode} from "react";
import "./Popup.css";

interface PopupProps {
    show: boolean;
    closeTrigger: () => void;
    component: ReactNode;
}

const Popup = (props:PopupProps) => {
    return <> {props.show && (<div className={"popup-parent"}>
        <div className={"backdrop"} onClick={props.closeTrigger}></div>
        <div className={"content box center"}>
            {props.component}
        </div>
    </div>)}</>;
}

export default Popup;