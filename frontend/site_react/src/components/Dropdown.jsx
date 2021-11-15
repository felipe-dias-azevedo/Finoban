import { FaCaretDown } from "react-icons/fa";

const Dropdown = ({ title, className, children }) => {

    return (
        <div class={`dropdown ${!className && ''}`}>
            <button class="dropdown-button">
                <span>{ title } </span>
                <FaCaretDown />
            </button>
            <div class="dropdown-content shadow">
                { children }
            </div>
        </div> 
    );
}

export default Dropdown;