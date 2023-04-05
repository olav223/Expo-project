import "./Dropdown.css";
import { ChangeEvent } from "react";

interface DropdownProps {
    options: string[],
    defaultOption?: string,
    onChange?: (value: string) => void,
    className?: string,
    id?: string,
}

/**
 * Dropdown component
 * @param options The options to display in the dropdown
 * @param defaultOption The default option to display
 * @param onChange The function to call when the selected option changes, the value of the selected option is passed as a parameter
 * @param className The css class to apply to the dropdown
 * @param id The id to apply to the dropdown
 * @author Martin Berg Alstad
 */
const Dropdown = ({ options, defaultOption = options[0], onChange, className, id }: DropdownProps) => {

    function handleSelect(e: ChangeEvent<HTMLSelectElement>): void {
        const newValue = e.target.value;
        if (onChange) {
            onChange(newValue);
        }
    }

    return <select onChange={ handleSelect } defaultValue={ defaultOption } className={ className } id={ id }>
        { options.map(option => <option key={ option }>{ option }</option>) }
    </select>;
};

export default Dropdown;
