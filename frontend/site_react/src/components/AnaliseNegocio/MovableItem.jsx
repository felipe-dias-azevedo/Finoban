import { useDrag } from "react-dnd";
import { Link } from "react-router-dom";
import { FiExternalLink } from "react-icons/fi";

function MovableItem({children, id, name, idGraph}) {

    const [, drag] = useDrag({
        item: {
            type: 'CHART',
            id,
            name
        },
        collect: monitor => ({
            isDragging: monitor.isDragging()
        })
    });

    return (
        <div
            ref={drag}
            className="movable-item chart-drag"
            id={id}
        >
            <Link to={`/analise/dashboard/${idGraph}`}>
                <h3>{name}</h3>
                <FiExternalLink />
            </Link>
            <div>
                {children}
            </div>
        </div>
    )
}

export default MovableItem;