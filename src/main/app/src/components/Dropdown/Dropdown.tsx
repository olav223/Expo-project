import "./Dropdown.css";
import { ChangeEvent } from "react";

interface DropdownProps {
    options: string[],
    defaultOption?: string,
    onChange?: (value: string) => void,
    className?: string,
    id?: string,
}

const Dropdown = ({ options, defaultOption = options[0], onChange, className, id }: DropdownProps) => {

    function handleSelect(e: ChangeEvent<HTMLSelectElement>) {
        const newValue = e.target.value;
        if (onChange) {
            onChange(newValue);
        }
    }

    return (
        <select onChange={ handleSelect } className={ className } id={ id }>
            {
                options.map(option => (
                    <option key={ option }>{ option }</option>
                ))
            }
        </select>
    );
};

export default Dropdown;
